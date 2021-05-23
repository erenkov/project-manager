package com.developing.simbir_product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;


@Entity
@Table(name = "task_release_history")
public class TaskReleaseHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "task_id", updatable = false, nullable = false)
    private TaskEntity taskId;

    @ManyToOne
    @JoinColumn(name = "release_id", updatable = false, nullable = false)
    private ReleaseEntity releaseId;

    @Column(name = "is_completed")
    private boolean isCompleted;

    public TaskReleaseHistoryEntity() {
    }

    public TaskReleaseHistoryEntity(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TaskEntity getTaskId() {
        return taskId;
    }

    public void setTaskId(TaskEntity taskId) {
        this.taskId = taskId;
    }

    public ReleaseEntity getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(ReleaseEntity releaseId) {
        this.releaseId = releaseId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
