package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.ReleaseRepository;
import com.developing.simbir_product.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ReleaseServiceImpl implements ReleaseService {


    @Autowired
    private ReleaseRepository releaseRepository;


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
    public ReleaseResponseDto addRelease(ReleaseRequestDto releaseRequestDto) {

        ReleaseEntity releaseEntity = new ReleaseEntity();
        //todo releaseEntity = mapFrom releaseRequestDto ???????????????????????????????

        releaseRepository.save(releaseEntity);

        return new ReleaseResponseDto(); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }


    @Transactional
    @Override
    public ReleaseResponseDto editRelease(ReleaseRequestDto releaseRequestDto) {

        ReleaseEntity releaseEntity = new ReleaseEntity();

        //todo releaseEntity = mapFrom releaseRequestDto ?????????????????????????????????????

        releaseRepository.save(releaseEntity);

        return new ReleaseResponseDto(); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
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
}
