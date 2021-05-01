package com.developing.simbir_product.entity;

import javax.persistence.*;

@Entity
@Table(name = "TASK_RELEASE_HISTORY")
public class TaskReleaseHistoryEntity {

    @EmbeddedId
    private TaskReleaseId taskReleaseId;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @ManyToOne
    @MapsId("taskId")
    private TaskEntity taskId;

    @ManyToOne
    @MapsId("releaseId")
    private ReleaseEntity releaseId;

    public TaskReleaseHistoryEntity() {
    }

    public TaskReleaseHistoryEntity(TaskReleaseId taskReleaseId, boolean isCompleted) {
        this.taskReleaseId = taskReleaseId;
        this.isCompleted = isCompleted;
    }

    public TaskReleaseId getTaskReleaseId() {
        return taskReleaseId;
    }

    public void setTaskReleaseId(TaskReleaseId taskReleaseId) {
        this.taskReleaseId = taskReleaseId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
