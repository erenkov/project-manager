package com.developing.simbir_product.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "USER_TASK_HISTORY")
public class UserTaskHistoryEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn("user_id")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn("task_id")
    private TaskEntity taskId;

    @Column(name = "valid_to_date")
    private OffsetDateTime validToDate;

    public UserTaskHistoryEntity() {
    }

    public UserTaskHistoryEntity(OffsetDateTime validToDate) {
        this.validToDate = validToDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public TaskEntity getTaskId() {
        return taskId;
    }

    public void setTaskId(TaskEntity taskId) {
        this.taskId = taskId;
    }

    public OffsetDateTime getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(OffsetDateTime validToDate) {
        this.validToDate = validToDate;
    }
}
