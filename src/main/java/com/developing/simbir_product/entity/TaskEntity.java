package com.developing.simbir_product.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TASK")
public class TaskEntity {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator", strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
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

    @Column(name = "priority", nullable = false)
    private int priority;

    public TaskEntity() {
    }

    public TaskEntity(String name, TaskStatus taskStatus, TaskType taskType,
                      String description, LocalDateTime createDate,
                      LocalDateTime dueDate, LocalDateTime finishDate,
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
