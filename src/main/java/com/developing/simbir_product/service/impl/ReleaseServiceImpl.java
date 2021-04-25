package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.repository.ReleaseRepository;
import com.developing.simbir_product.service.TaskReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseServiceImpl implements TaskReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

}
