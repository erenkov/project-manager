package com.developing.simbir_product.service;

import com.developing.simbir_product.entity.ReleaseEntity;

import java.util.UUID;


public interface ReleaseService {

    void addRelease();

    ReleaseEntity getById(UUID id);


//    private ReleaseResponseDto getById(UUID is) {
//        return null;
//    }
}
