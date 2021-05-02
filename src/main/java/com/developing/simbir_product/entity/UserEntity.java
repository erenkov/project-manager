package com.developing.simbir_product.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usr")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "pass", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_number", nullable = false)
    private int userNumber;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) // @ElementCollection is mainly for mapping non-entities (embeddable or basic)
    // while @OneToMany is used to map entities.

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private Set<Role> role;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity teamId;

    public UserEntity() {
    }

    public UserEntity(String login, String password, String firstName,
                      String lastName, TeamEntity teamId, Set<Role> role) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamId = teamId;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public TeamEntity getTeamId() {
        return teamId;
    }

    public void setTeamId(TeamEntity teamId) {
        this.teamId = teamId;
    }
}
