package com.developing.simbir_product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "TASK")
public class TaskRelease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "task_id")
    private UUID taskId;

    @Column( name = "release_id")
    private UUID releaseId;

    @Column(name = "isComplete")
    private int isComplete;

    public TaskRelease() {
    }

    public TaskRelease(UUID id, UUID taskId, UUID releaseId, int isComplete) {
        this.id = id;
        this.taskId = taskId;
        this.releaseId = releaseId;
        this.isComplete = isComplete;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(UUID releaseId) {
        this.releaseId = releaseId;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }
}
