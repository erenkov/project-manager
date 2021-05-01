package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;

import java.util.UUID;

public interface ReleaseService {

    ReleaseResponseDto getById(UUID id);

    ReleaseResponseDto addRelease(ReleaseRequestDto releaseRequestDto);

    ReleaseResponseDto editRelease(ReleaseRequestDto releaseRequestDto);

    void deleteById(UUID id);

    ReleaseResponseDto findByName(String name);

}
