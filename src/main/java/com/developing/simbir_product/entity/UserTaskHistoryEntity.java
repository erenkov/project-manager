package com.developing.simbir_product.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
@DynamicInsert
@Table(name = "user_task_history")
public class UserTaskHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "task_id")
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
