package com.developing.simbir_product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "teamId")
    private List<ProjectEntity> projects;

    @OneToMany(mappedBy = "teamId")
    private List<UserEntity> users;

    public TeamEntity() {
    }

    public TeamEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void assignProjectToTeam(ProjectEntity project) {
        if (projects == null) {
            projects = new ArrayList<>();
        }
        projects.add(project);
        project.setTeamId(this);
    }

    public void assignUserToTeam(UserEntity user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
        user.setTeamId(this);
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

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
