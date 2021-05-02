package com.developing.simbir_product.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "task_status", nullable = false)
    private TaskStatus taskStatus;

    @Column(name = "task_type", nullable = false)
    private TaskType taskType;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity projectId;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date", nullable = false)
    private OffsetDateTime createDate;

    @Column(name = "due_date")
    private OffsetDateTime dueDate;

    @Column(name = "finish_date")
    private OffsetDateTime finishDate;

    @Column(name = "est_costs")
    private int estCosts;

    @Column(name = "actual_costs")
    private int actualCosts;

    @Column(name = "comments")
    private String comments;

    @Column(name = "priority", nullable = false)
    private int priority;

    public TaskEntity() {
    }

    public TaskEntity(String name, TaskStatus taskStatus, TaskType taskType,
                      String description, OffsetDateTime createDate,
                      OffsetDateTime dueDate, OffsetDateTime finishDate,
                      int estCosts, int actualCosts, String comments, int priority) {
        this.name = name;
        this.taskStatus = taskStatus;
        this.taskType = taskType;
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

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public ProjectEntity getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectEntity projectId) {
        this.projectId = projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(OffsetDateTime createDate) {
        this.createDate = createDate;
    }

    public OffsetDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(OffsetDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public OffsetDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(OffsetDateTime finishDate) {
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
