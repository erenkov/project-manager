package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskReleaseHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface TaskReleaseHistoryRepository extends JpaRepository<TaskReleaseHistoryEntity, UUID> {

    List<TaskReleaseHistoryEntity> findByTaskId(TaskEntity taskEntity);

    List<TaskReleaseHistoryEntity> findByReleaseId(ReleaseEntity releaseEntity);

}
