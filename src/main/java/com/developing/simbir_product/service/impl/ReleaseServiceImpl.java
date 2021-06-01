package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.exception.ReleaseDatesException;
import com.developing.simbir_product.mappers.DateTimeMapper;
import com.developing.simbir_product.mappers.ReleaseMapper;
import com.developing.simbir_product.repository.ReleaseRepository;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TaskReleaseHistoryService;
import com.developing.simbir_product.utils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final Logger logger = LoggerFactory.getLogger(ReleaseServiceImpl.class);

    @Autowired
    private ReleaseMapper releaseMapper;

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskReleaseHistoryService taskReleaseHistoryService;

    @Autowired
    private DateTimeMapper dateTimeMapper;

    @Autowired
    private MessageSource messageSource;


    @Transactional
    @Override
    public ReleaseResponseDto getById(UUID id) {
        if (id == null) {
            throw new NotFoundException(messageSource.getMessage("releaseService.getById.notFound",
                    null, LocaleContextHolder.getLocale()));
        }
        ReleaseEntity releaseEntity = releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("releaseService.notFoundById",
                        new UUID[]{id}, LocaleContextHolder.getLocale())));
        return releaseMapper.releaseEntityToDto(releaseEntity);
    }

    @Transactional
    @Override
    public ReleaseResponseDto getByStringId(String id) {
        if (!Converter.isValidUuid(id)) {
            throw new NotFoundException(messageSource.getMessage("releaseService.notFoundById",
                    new String[]{id}, LocaleContextHolder.getLocale()));
        }
        return getById(Converter.getUuidFromString(id));
    }

    @Transactional
    @Override
    public boolean addRelease(ReleaseRequestDto releaseRequestDto) {
        if (countIntersectingReleases(releaseRequestDto) > 0) {
            throw new ReleaseDatesException("releaseDatesIntersection.message", releaseRequestDto, messageSource);
        }
        checkReleaseDates(releaseRequestDto);
        releaseRepository.save(releaseMapper.releaseDtoToEntity(releaseRequestDto));
        logger.info(messageSource.getMessage("releaseService.addRelease.logger",
                new String[]{releaseRequestDto.getName(), releaseRequestDto.getProjectName()}, Locale.getDefault()));
        return true;
    }

    @Transactional
    @Override
    public boolean editRelease(ReleaseRequestDto releaseRequestDto) {
        UUID uuid = Converter.getUuidFromString(releaseRequestDto.getId());
        if (uuid == null || !releaseRepository.existsById(uuid)) {
            throw new NotFoundException(messageSource.getMessage("releaseService.getById.notFound",
                    null, LocaleContextHolder.getLocale()));
        }
        if (countIntersectingReleases(releaseRequestDto) > 1) {
            throw new ReleaseDatesException("releaseDatesIntersection.message", releaseRequestDto, messageSource);
        }
        checkReleaseDates(releaseRequestDto);
        ReleaseEntity releaseEntity = releaseMapper.releaseDtoToEntity(releaseRequestDto);
        releaseRepository.save(releaseEntity);

        logger.info(messageSource.getMessage("releaseService.editRelease.logger",
                new String[]{releaseRequestDto.getName(), releaseRequestDto.getProjectName()}, Locale.getDefault()));
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
                () -> new NotFoundException(messageSource.getMessage("releaseService.getCurrentRelease.notFound",
                        new String[]{projectName}, LocaleContextHolder.getLocale())));
        return releaseMapper.releaseEntityToDto(releaseEntity);
    }

    @Transactional
    @Override
    public List<ReleaseResponseDto> getAllReleasesByProject(ProjectEntity projectEntity) {
        List<ReleaseEntity> releaseEntities = releaseRepository.findAllByProjectIdOrderByStartDateDesc(projectEntity).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("releaseService.getCurrentRelease.notFound",
                        new String[]{projectEntity.getName()}, LocaleContextHolder.getLocale())));
        return releaseEntities.stream()
                .map(releaseMapper::releaseEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ReleaseEntity getEntityById(UUID id) {
        return releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("releaseService.notFoundById",
                        new UUID[]{id}, LocaleContextHolder.getLocale())));
    }

    private void checkReleaseDates(ReleaseRequestDto releaseRequestDto) {
        ProjectEntity project = projectService.getProjectEntity(releaseRequestDto.getProjectName());
        if (project.getStartDate() != null && project.getEstFinishDate() != null &&
                (releaseRequestDto.getStartDate().isBefore(project.getStartDate().toLocalDateTime()) ||
                        releaseRequestDto.getFinishDate().isAfter(project.getEstFinishDate().toLocalDateTime()))) {
            throw new ReleaseDatesException("releaseDatesOutside.message", releaseRequestDto, messageSource);
        }
    }

    private long countIntersectingReleases(ReleaseRequestDto releaseToCheck) {
        ProjectEntity projectEntity = projectService.getProjectEntity(releaseToCheck.getProjectName());
        return getAllReleasesByProject(projectEntity).stream()
                .filter(existingRelease -> !(releaseToCheck.getStartDate().isAfter(existingRelease.getFinishDate()) ||
                        releaseToCheck.getFinishDate().isBefore(existingRelease.getStartDate())))
                .count();
    }
}
