package com.developing.simbir_product.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class UserTaskId implements Serializable {

    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator", strategy = GenerationType.AUTO)
    @Column(name = "task_id", updatable = false, nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID taskId;

    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator", strategy = GenerationType.AUTO)
    @Column(name = "user_id", updatable = false, nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID userId;

    @Column(name = "to_date")
    private LocalDateTime toDate;

    public UserTaskId() {
    }

    public UserTaskId(UUID taskId, UUID userId, LocalDateTime toDate) {
        this.taskId = taskId;
        this.userId = userId;
        this.toDate = toDate;
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

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTaskId that = (UserTaskId) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(userId, that.userId)
                && Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, userId, toDate);
    }
}
