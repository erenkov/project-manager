package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.repository.TaskReleaseRepository;
import com.developing.simbir_product.service.TaskReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskReleaseServiceImpl implements TaskReleaseService {

    @Autowired
    private TaskReleaseRepository taskReleaseRepository;
}
