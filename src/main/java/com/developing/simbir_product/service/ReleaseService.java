package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;

import java.util.List;
import java.util.UUID;

public interface ReleaseService {

    ReleaseResponseDto getById(UUID id);

    boolean addRelease(ReleaseRequestDto releaseRequestDto);

    boolean editRelease(ReleaseRequestDto releaseRequestDto);

    void deleteById(UUID id);

    ReleaseResponseDto findByName(String name);

    ReleaseEntity addReleaseEntity(ReleaseEntity releaseEntity);

    String getReleaseString(TaskEntity taskEntity);

    ReleaseResponseDto getCurrentRelease(String projectName);
    List<ReleaseResponseDto> getAllReleasesByProject(ProjectEntity projectEntity);
}
