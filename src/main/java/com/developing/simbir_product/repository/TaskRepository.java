package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskStatus;
import com.developing.simbir_product.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.loader.Loader.SELECT;
import static org.hibernate.sql.ast.Clause.WHERE;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    Optional<TaskEntity> findByName(String name);

    @Query("SELECT c FROM TaskEntity c WHERE" +
            " (:name is null or c.name = :name) and (:estCosts is null or c.estCosts = :estCosts)" +
            " and (:actualCosts is null or c.actualCosts = :actualCosts) and (:projectId is null or c.projectId = :projectId)" +
            " and (:priority is null or c.priority = :priority) and (:description is null or c.description = :description)" +
            " and (:comments is null or c.comments = :comments)")
    List<TaskEntity> findByNameAndEstCostsAndActualCostsAndProjectIdAndPriorityAndDescriptionAndComments(
            String name, int estCosts, int actualCosts, ProjectEntity projectId, int priority, String description,
            String comments);
}
