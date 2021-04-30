package com.developing.simbir_product.entity;

import javax.persistence.*;
import java.util.UUID;


@Entity
@IdClass(TaskReleaseId.class)
@Table(name = "TASK_RELEASE")
public class TaskReleaseEntity {

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Id
    private UUID taskId;

    @Id
    private UUID releaseId;


    public TaskReleaseEntity() {
    }

    public TaskReleaseEntity(boolean isCompleted, UUID taskId, UUID releaseId) {
        this.isCompleted = isCompleted;
        this.taskId = taskId;
        this.releaseId = releaseId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
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
}
