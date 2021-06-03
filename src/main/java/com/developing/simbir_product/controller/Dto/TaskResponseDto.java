package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Schema(description = "{taskDto.schema}")
public class TaskResponseDto {

    @Schema(description = "{taskDto.id.schema}")
    private String id;

    @Schema(description = "{taskDto.name.schema}")
    private String name;

    @Schema(description = "{taskDto.status.schema}")
    private String status;

    @Schema(description = "{taskDto.type.schema}")
    private String type;

    @Schema(description = "{taskDto.description.schema}")
    private String description;

    @Schema(description = "{taskDto.createDate.schema}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createDate;

    @Schema(description = "{taskDto.dueDate.schema}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dueDate;

    @Schema(description = "{taskDto.finishDate.schema}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime finishDate;

    @Schema(description = "{taskDto.estCosts.schema}")
    private Integer estCosts;

    @Schema(description = "{taskDto.actualCosts.schema}")
    private Integer actualCosts;

    @Schema(description = "{taskDto.comments.schema}")
    private String comments;

    @Schema(description = "{taskDto.priority.schema}")
    private Integer priority;

    @Schema(description = "{taskDto.assigneeName.schema}")
    private String assigneeName;

    @Schema(description = "{teamDto.name.schema}")
    private String team;

    @Schema(description = "{releaseDto.name.schema}")
    private String release;

    @Schema(description = "{projectDto.name.schema}")
    private String projectName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getEstCosts() {
        return estCosts;
    }

    public void setEstCosts(Integer estCosts) {
        this.estCosts = estCosts;
    }

    public Integer getActualCosts() {
        return actualCosts;
    }

    public void setActualCosts(Integer actualCosts) {
        this.actualCosts = actualCosts;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
