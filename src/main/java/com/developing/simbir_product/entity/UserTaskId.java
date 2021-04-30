package com.developing.simbir_product.entity;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;


public class UserTaskId implements Serializable {

    @Column(name = "task_id", updatable = false, nullable = false)
    private UUID taskId;

    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "valid_to_date")
    private OffsetDateTime validToDate;


    public UserTaskId() {
    }

    public UserTaskId(UUID taskId, UUID userId, OffsetDateTime validToDate) {
        this.taskId = taskId;
        this.userId = userId;
        this.validToDate = validToDate;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public OffsetDateTime getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(OffsetDateTime validToDate) {
        this.validToDate = validToDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTaskId that = (UserTaskId) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(userId, that.userId)
                && Objects.equals(validToDate, that.validToDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, userId, validToDate);
    }
}
