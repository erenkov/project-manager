package com.developing.simbir_product.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class TaskReleaseId implements Serializable {

//    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
//    @GeneratedValue(generator = "UUIDGenerator", strategy = GenerationType.AUTO)
//    @Column(name = "task_id", updatable = false, nullable = false)
//    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID taskId;

//    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
//    @GeneratedValue(generator = "UUIDGenerator", strategy = GenerationType.AUTO)
//    @Column(name = "release_id", updatable = false, nullable = false)
//    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID releaseId;

    public TaskReleaseId() {
    }

    public TaskReleaseId(UUID taskId, UUID releaseId) {
        this.taskId = taskId;
        this.releaseId = releaseId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(UUID releaseId) {
        this.releaseId = releaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskReleaseId that = (TaskReleaseId) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(releaseId, that.releaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, releaseId);
    }
}
