package com.developing.simbir_product.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PROJECT")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity teamId;

    @Column(name = "project_status")
    private ProjectStatus projectStatus;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "est_finish_date")
    private LocalDateTime estFinishDate;

    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    public ProjectEntity() {
    }

    public ProjectEntity(String name, String description, ProjectStatus projectStatus,
                         LocalDateTime startDate, LocalDateTime estFinishDate,
                         LocalDateTime finishDate) {
        this.name = name;
        this.description = description;
        this.projectStatus = projectStatus;
        this.startDate = startDate;
        this.estFinishDate = estFinishDate;
        this.finishDate = finishDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TeamEntity getTeamId() {
        return teamId;
    }

    public void setTeamId(TeamEntity teamId) {
        this.teamId = teamId;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEstFinishDate() {
        return estFinishDate;
    }

    public void setEstFinishDate(LocalDateTime estFinishDate) {
        this.estFinishDate = estFinishDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }
}
