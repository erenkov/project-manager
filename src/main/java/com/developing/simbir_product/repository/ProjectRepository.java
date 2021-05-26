package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, UUID> {

    Optional<ProjectEntity> findByName(String name);

    List<ProjectEntity> findAllByTeamId(TeamEntity team);

    List<ProjectEntity> findAllByOrderByNameAsc();
}
