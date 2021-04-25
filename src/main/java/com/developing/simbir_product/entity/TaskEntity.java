package com.developing.simbir_product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "state_id")
    private UUID stateId;

    @Column(name = "task_type_id")
    private UUID taskTypeId;

    @Column(name = "project_id")
    private UUID projectId;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    @Column(name = "est_costs")
    private int estCosts;

    @Column(name = "actual_costs")
    private int actualCosts;

    @Column(name = "comments")
    private String comments;

    @Column(name = "priority")
    private int priority;

    public Task() {
    }

    public Task(UUID id, String name, UUID stateId, UUID taskTypeId, UUID projectId, String description,
                LocalDateTime createDate, LocalDateTime dueDate, LocalDateTime finishDate, int estCosts,
                int actualCosts, String comments, int priority) {
        this.id = id;
        this.name = name;
        this.stateId = stateId;
        this.taskTypeId = taskTypeId;
        this.projectId = projectId;
        this.description = description;
        this.createDate = createDate;
        this.dueDate = dueDate;
        this.finishDate = finishDate;
        this.estCosts = estCosts;
        this.actualCosts = actualCosts;
        this.comments = comments;
        this.priority = priority;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getStateId() {
        return stateId;
    }

    public void setStateId(UUID stateId) {
        this.stateId = stateId;
    }

    public UUID getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(UUID taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public int getEstCosts() {
        return estCosts;
    }

    public void setEstCosts(int estCosts) {
        this.estCosts = estCosts;
    }

    public int getActualCosts() {
        return actualCosts;
    }

    public void setActualCosts(int actualCosts) {
        this.actualCosts = actualCosts;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
