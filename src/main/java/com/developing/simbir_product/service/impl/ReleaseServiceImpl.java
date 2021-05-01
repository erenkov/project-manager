package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.repository.ReleaseRepository;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TaskReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;


@Service
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    @Override
    public void addRelease() {
        ReleaseEntity releaseEntity = new ReleaseEntity();
        releaseEntity.setName("Name");
        releaseEntity.setStartDate(OffsetDateTime.now());
        releaseEntity.setFinishDate(OffsetDateTime.now());

        releaseRepository.save(releaseEntity);
    }

    @Override
    public ReleaseEntity getById(UUID id) {
        return releaseRepository.getOne(id);
    }


}
