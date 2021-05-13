package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.mappers.DateTimeMapper;
import com.developing.simbir_product.mappers.ReleaseMapper;
import com.developing.simbir_product.repository.ReleaseRepository;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TaskReleaseHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ReleaseServiceImpl implements ReleaseService {

    Logger logger = LoggerFactory.getLogger(ReleaseServiceImpl.class);

    @Autowired
    ReleaseMapper releaseMapper;

    @Autowired
    private ReleaseRepository releaseRepository;

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
//        Optional<ReleaseEntity> tempReleaseFromDb = releaseRepository.findById(Converter.getUuidFromString(releaseRequestDto.getId()));
//
//        if (tempReleaseFromDb.isPresent()) { // Если при добавлении релиза в БД уже найден релиз с
//            return false;                    // тем же именем, то не записываем релиз в БД
//        }

        releaseRepository.save(releaseMapper.releaseDtoToEntity(releaseRequestDto));
        logger.trace(releaseRequestDto.getName() + " release for " + releaseRequestDto.getProjectName() + " has been added");
        return true;
    }


    @Transactional
    @Override
    public boolean editRelease(ReleaseRequestDto releaseRequestDto) {
        ReleaseEntity releaseEntity = releaseMapper.releaseDtoToEntity(releaseRequestDto);
        Optional<ReleaseEntity> tempReleaseFromDB = Optional.ofNullable(getEntityById(releaseEntity.getId()));

        if (tempReleaseFromDB.isEmpty()) { // Если при редактировании текущий релиз в БД не найден,
            return false;                  // то не выполняем запись в БД
        }

        releaseEntity.setId(tempReleaseFromDB.get().getId());
        releaseRepository.save(releaseEntity);
        logger.trace(releaseRequestDto.getName() + " release for " + releaseRequestDto.getProjectName() + " has been edited");
        return true;
    }


    @Transactional
    @Override
    public void deleteById(UUID id) {
        releaseRepository.deleteById(id); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }


    @Transactional
    @Override
    public ReleaseResponseDto findByName(String name) {

        ReleaseEntity releaseEntity = releaseRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Release with name = '%s' not found", name)));

        ReleaseResponseDto releaseResponseDto = new ReleaseResponseDto();
        //todo ReleaseResponseDto = mapFrom releaseEntity !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        return releaseResponseDto;
    }

    @Transactional
    @Override
    public ReleaseEntity addReleaseEntity(ReleaseEntity releaseEntity) {
        return releaseRepository.save(releaseEntity);
    }

    public String getReleaseString(TaskEntity taskEntity) {
        ReleaseEntity release = null;
        try {
            release = taskReleaseHistoryService.getCurrentReleaseByTask(taskEntity);
        } catch (NotFoundException e) {
            return "";
        }
        return String.format("%s (%s - %s)",
                release.getName(),
                dateTimeMapper.dateToString(release.getStartDate()),
                dateTimeMapper.dateToString(release.getFinishDate()));
    }

    @Transactional
    @Override
    public List<ReleaseResponseDto> findAll() {
        return releaseRepository
                .findAll()
                .stream()
                .map(rE -> releaseMapper.releaseEntityToDto(rE))
                .collect(Collectors.toList());
    }

    private ReleaseEntity getEntityById(UUID id) {
        return releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Release with ID = '%s' not found", id)));
    }
}
