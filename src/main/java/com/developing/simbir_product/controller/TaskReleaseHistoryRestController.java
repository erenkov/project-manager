package com.developing.simbir_product.controller;

import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TaskReleaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taskRelease")
public class TaskReleaseHistoryRestController {

    @Autowired
    TaskReleaseHistoryService taskReleaseHistoryService;

    @Autowired
    ReleaseService releaseService;

    @GetMapping("{name}")
    public List<TaskEntity> getUnfinishedTasksByRelease(@PathVariable String name){
        ReleaseEntity releaseEntity = releaseService.getByName(name);
        List<TaskEntity> taskEntities = taskReleaseHistoryService.findUnfinishedTasksByReleaseId(releaseEntity);
        return taskEntities;
    }
}
