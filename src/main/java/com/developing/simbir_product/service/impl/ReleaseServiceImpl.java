package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.mappers.DateTimeMapper;
import com.developing.simbir_product.mappers.ReleaseMapper;
import com.developing.simbir_product.repository.ReleaseRepository;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TaskReleaseHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final Logger logger = LoggerFactory.getLogger(ReleaseServiceImpl.class);

    @Autowired
    ReleaseMapper releaseMapper;

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskReleaseHistoryService taskReleaseHistoryService;

    @Autowired
    private DateTimeMapper dateTimeMapper;


    @Transactional
    @Override
    public ReleaseResponseDto getById(UUID id) {
        ReleaseEntity releaseEntity = releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Release with ID = '%s' not found", id)));

        return releaseMapper.releaseEntityToDto(releaseEntity);
    }

    @Transactional
    @Override
    public boolean addRelease(ReleaseRequestDto releaseRequestDto) {
        if (countIntersectingReleases(releaseRequestDto) > 0) {
            return false;
        }
        releaseRepository.save(releaseMapper.releaseDtoToEntity(releaseRequestDto));
        logger.trace(String.format("%s release for %s has been added",
                releaseRequestDto.getName(),
                releaseRequestDto.getProjectName()));
        return true;
    }

    private long countIntersectingReleases(ReleaseRequestDto releaseToCheck) {
        ProjectEntity projectEntity = projectService.getProjectEntity(releaseToCheck.getProjectName());
        return getAllReleasesByProject(projectEntity).stream()
                .filter(existingRelease -> !(releaseToCheck.getStartDate().isAfter(existingRelease.getFinishDate()) ||
                        releaseToCheck.getFinishDate().isBefore(existingRelease.getStartDate())))
                .count();
    }

    @Transactional
    @Override
    public boolean editRelease(ReleaseRequestDto releaseRequestDto) {
        ReleaseEntity releaseEntity = releaseMapper.releaseDtoToEntity(releaseRequestDto);
        Optional<ReleaseEntity> tempReleaseFromDB = Optional.ofNullable(getEntityById(releaseEntity.getId()));

        if (tempReleaseFromDB.isEmpty() || countIntersectingReleases(releaseRequestDto) > 1) { // Если при редактировании текущий релиз в БД не найден,
            return false;                  // то не выполняем запись в БД
        }

        releaseEntity.setId(tempReleaseFromDB.get().getId());
        releaseRepository.save(releaseEntity);
        logger.trace(String.format("%s release for %s has been edited",
                releaseRequestDto.getName(),
                releaseRequestDto.getProjectName()));
        return true;
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        releaseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ReleaseEntity addReleaseEntity(ReleaseEntity releaseEntity) {
        return releaseRepository.save(releaseEntity);
    }

    @Override
    public String getReleaseString(TaskEntity taskEntity) {
        ReleaseEntity release = taskReleaseHistoryService.getCurrentReleaseByTask(taskEntity);
        if (release == null) {
            return "";
        }
        return String.format("%s (%s - %s)",
                release.getName(),
                dateTimeMapper.dateToString(release.getStartDate()),
                dateTimeMapper.dateToString(release.getFinishDate()));
    }

    @Transactional
    @Override
    public ReleaseResponseDto getCurrentRelease(String projectName) {
        ProjectEntity projectEntity = projectService.getProjectEntity(projectName);
        List<ReleaseResponseDto> allReleasesByProject = getAllReleasesByProject(projectEntity);
        if (allReleasesByProject.stream().noneMatch(release -> LocalDateTime.now().isAfter(release.getStartDate()) &&
                LocalDateTime.now().isBefore(release.getFinishDate()))) {
            return new ReleaseResponseDto();
        }
        ReleaseEntity releaseEntity = releaseRepository.getCurrentRelease(projectEntity.getId()).orElseThrow(
                () -> new NotFoundException(String.format("Release for project with name = '%s' not found", projectName))
        );
        return releaseMapper.releaseEntityToDto(releaseEntity);
    }

    @Transactional
    @Override
    public List<ReleaseResponseDto> getAllReleasesByProject(ProjectEntity projectEntity) {
        return releaseRepository.findAllByProjectIdOrderByStartDateDesc(projectEntity).orElseThrow(
                () -> new NotFoundException(String.format("Project with name = '%s' not found", projectEntity.getName()))
        ).stream().map(releaseMapper::releaseEntityToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ReleaseEntity getEntityById(UUID id) {
        return releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Release with ID = '%s' not found", id)));
    }
}
