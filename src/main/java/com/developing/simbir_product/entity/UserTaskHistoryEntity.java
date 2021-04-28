package com.developing.simbir_product.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER_TASK_HISTORY")
public class UserTaskHistoryEntity {

    @EmbeddedId
    private UserTaskId userTaskId;

    @ManyToOne
    @MapsId("userId")
    private UserEntity userId;

    @ManyToOne
    @MapsId("taskId")
    private TaskEntity taskId;

    public UserTaskHistoryEntity() {
    }

    public UserTaskHistoryEntity(UserTaskId userTaskId) {
        this.userTaskId = userTaskId;
    }

    public UserTaskId getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(UserTaskId userTaskId) {
        this.userTaskId = userTaskId;
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
}
