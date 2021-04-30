package com.developing.simbir_product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
@IdClass(UserTaskId.class)
@Table(name = "USER_TASK_HISTORY")
public class UserTaskHistoryEntity {

    @Id
    private UUID userId;

    @Id
    private UUID taskId;

    @Id
    private OffsetDateTime validToDate;


    public UserTaskHistoryEntity(UUID userId, UUID taskId, OffsetDateTime validToDate) {
        this.userId = userId;
        this.taskId = taskId;
        this.validToDate = validToDate;
    }

    public UserTaskHistoryEntity() {
    }

    public OffsetDateTime getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(OffsetDateTime validToDate) {
        this.validToDate = validToDate;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }
}
