package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends  JpaRepository<ProjectEntity, UUID>{

}
