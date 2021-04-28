package com.developing.simbir_product.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "RELEASE")
public class ReleaseEntity {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator", strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "finish_date", nullable = false)
    private LocalDateTime finishDate;

    public ReleaseEntity() {
    }

    public ReleaseEntity(String name, LocalDateTime startDate, LocalDateTime finishDate) {
        this.name = name;
        this.startDate = startDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }
}
