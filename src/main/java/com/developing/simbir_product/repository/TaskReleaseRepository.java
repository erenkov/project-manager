package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.TaskReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskReleaseRepository extends JpaRepository<TaskReleaseEntity, UUID>{

    Optional<TaskReleaseEntity> findByTaskIdAndReleaseId(UUID taskId, UUID releaseId);

    void deleteByTaskIdAndReleaseId(UUID taskId, UUID releaseId);

}
