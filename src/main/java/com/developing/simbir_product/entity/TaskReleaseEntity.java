package com.developing.simbir_product.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TASK")
public class TaskReleaseEntity {

    @EmbeddedId
    private TaskReleaseId taskReleaseId;

    @Column(name = "is_complete")
    private int isComplete;

    public TaskReleaseEntity() {
    }

    public TaskReleaseEntity(TaskReleaseId taskReleaseId, int isComplete) {
        this.taskReleaseId = taskReleaseId;
        this.isComplete = isComplete;
    }

    public TaskReleaseId getTaskReleaseId() {
        return taskReleaseId;
    }

    public void setTaskReleaseId(TaskReleaseId taskReleaseId) {
        this.taskReleaseId = taskReleaseId;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }
}
