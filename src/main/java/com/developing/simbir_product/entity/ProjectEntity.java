package com.developing.simbir_product.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PROJECT")
public class ProjectEntity {

//    @Id
//    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
//    @GeneratedValue(generator = "UUIDGenerator", strategy = GenerationType.AUTO)
//    @Column(name = "id", updatable = false, nullable = false)
//    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity teamId;

    @Column(name = "project_status")
    private ProjectStatus projectStatus;

    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @Column(name = "est_finish_date")
    private OffsetDateTime estFinishDate;

    @Column(name = "finish_date")
    private OffsetDateTime finishDate;

    @OneToMany (mappedBy = "projectId")
    private List<TaskEntity> tasks;

    public ProjectEntity() {
    }


    public ProjectEntity(String name, String description, ProjectStatus projectStatus,
                         OffsetDateTime startDate, OffsetDateTime estFinishDate,
                         OffsetDateTime finishDate) {
        this.name = name;
        this.description = description;
        this.projectStatus = projectStatus;
        this.startDate = startDate;
        this.estFinishDate = estFinishDate;
        this.finishDate = finishDate;
    }

    public void assignTaskToProject (TaskEntity task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        task.setProjectId(this);
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

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEstFinishDate() {
        return estFinishDate;
    }

    public void setEstFinishDate(OffsetDateTime estFinishDate) {
        this.estFinishDate = estFinishDate;
    }

    public OffsetDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(OffsetDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
