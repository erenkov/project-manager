package com.developing.simbir_product.controller.Dto;

import com.developing.simbir_product.validators.TaskWithDates;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Schema(description = "{taskDto.schema}")
@TaskWithDates
public class TaskRequestDto {

    @Schema(description = "{taskDto.id.schema}")
    private String id;

    @Schema(description = "{taskDto.name.schema}")
    @NotBlank(message = "{taskDto.name.notBlank}")
    @Size(max = 50, message = "{taskDto.name.size}")
    private String name;

    @Schema(description = "{taskDto.status.schema}")
    private String status;

    @Schema(description = "{taskDto.type.schema}")
    private String type;

    @Schema(description = "{taskDto.description.schema}")
    private String description;

    @Schema(description = "{taskDto.createDate.schema}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "{taskDto.createDate.notNull}")
    private LocalDateTime createDate;

    @Schema(description = "{taskDto.dueDate.schema}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "{taskDto.dueDate.notNull}")
    private LocalDateTime dueDate;

    @Schema(description = "{taskDto.finishDate.schema}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime finishDate;

    @Schema(description = "{taskDto.estCosts.schema}")
    @NumberFormat
    private Integer estCosts;

    @Schema(description = "{taskDto.actualCosts.schema}")
    @NumberFormat
    private Integer actualCosts;

    @Schema(description = "{taskDto.comments.schema}")
    private String comments;

    @Schema(description = "{taskDto.priority.schema}")
    private Integer priority;

    @Schema(description = "{taskDto.assigneeName.schema}")
    @Size(max = 150, message = "{taskDto.assigneeName.size}")
    private String assigneeName;

    @Schema(description = "{teamDto.name.schema}")
    @Size(max = 50, message = "{teamDto.name.size}")
    private String team;

    @Schema(description = "{releaseDto.name.schema}")
    @Size(max = 50, message = "{releaseDto.name.size}")
    private String release;

    @Schema(description = "{projectDto.name.schema}")
    @Size(max = 50, message = "{projectDto.name.size}")
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
