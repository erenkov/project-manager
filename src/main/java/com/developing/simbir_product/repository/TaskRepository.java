package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
    Optional<TaskEntity> findByName(String name);
    Optional<List<TaskEntity>> findByTaskStatus(TaskStatus taskStatus);
}
