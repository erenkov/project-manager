package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
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

import java.util.UUID;


@Service
public class ReleaseServiceImpl implements ReleaseService {
    Logger logger = LoggerFactory.getLogger(ReleaseServiceImpl.class);
    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskReleaseHistoryService taskReleaseHistoryService;

    @Autowired
    private DateTimeMapper dateTimeMapper;

    @Autowired
    private ReleaseMapper releaseMapper;

    @Transactional
    @Override
    public ReleaseResponseDto getById(UUID id) {

        ReleaseEntity releaseEntity = releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Release with ID = '%s' not found", id)));

        ReleaseResponseDto releaseResponseDto = new ReleaseResponseDto();

        //todo ReleaseResponseDto = mapFrom releaseEntity !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        return releaseResponseDto;
    }


    @Transactional
    @Override
    public boolean addRelease(ReleaseRequestDto releaseRequestDto) {

        ReleaseEntity releaseEntity = new ReleaseEntity();
        //todo releaseEntity = mapFrom releaseRequestDto ???????????????????????????????

        releaseRepository.save(releaseEntity);
        logger.trace(releaseRequestDto.getName() + " release for " + releaseRequestDto.getProjectName() + " has been added");
//        return new ReleaseResponseDto(); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
        return false;
    }


    @Transactional
    @Override
    public boolean editRelease(ReleaseRequestDto releaseRequestDto) {

        ReleaseEntity releaseEntity = new ReleaseEntity();

        //todo releaseEntity = mapFrom releaseRequestDto ?????????????????????????????????????

        releaseRepository.save(releaseEntity);
        logger.trace(releaseRequestDto.getName() + " release for " + releaseRequestDto.getProjectName() + " has been edited");
        //        return new ReleaseResponseDto(); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
        return false;
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

    //данный метод думаю не акутален после того как мы ввели новое поле project_id
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
    public ReleaseResponseDto getCurrentRelease(String projectName) {
        ReleaseEntity releaseEntity = releaseRepository.getCurrentRelease(projectService.getProjectEntity(projectName).getId()).orElseThrow(
                () -> new NotFoundException(String.format("Release for project with name = '%s' not found", projectName))
        );
        return releaseMapper.releaseEntityToDto(releaseEntity);
    }

}
