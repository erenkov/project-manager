package com.developing.simbir_product.entity;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


public class TaskReleaseId implements Serializable {

    @Column(name = "task_id", updatable = false, nullable = false)
    private UUID taskId;

    @Column(name = "release_id", updatable = false, nullable = false)
    private UUID releaseId;


    public TaskReleaseId() {
    }

    public TaskReleaseId(UUID taskId, UUID releaseId) {
        this.taskId = taskId;
        this.releaseId = releaseId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskReleaseId that = (TaskReleaseId) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(releaseId, that.releaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, releaseId);
    }
}
