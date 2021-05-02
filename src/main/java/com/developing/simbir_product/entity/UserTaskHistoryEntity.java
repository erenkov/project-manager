package com.developing.simbir_product.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER_TASK_HISTORY")
public class UserTaskHistoryEntity {

    @EmbeddedId
    private UserTaskId userTaskId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity taskId;

    public UserTaskHistoryEntity() {
    }

    public UserTaskId getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(UserTaskId userTaskId) {
        this.userTaskId = userTaskId;
    }
}
