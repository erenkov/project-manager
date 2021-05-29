package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.entity.UserTaskHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserTaskHistoryRepository extends JpaRepository<UserTaskHistoryEntity, UUID> {

    Optional<UserTaskHistoryEntity> findByTaskIdAndValidToDateIsAfter(TaskEntity taskId, OffsetDateTime validToDate);

    List<UserTaskHistoryEntity> findAllByUserId(UserEntity userEntity);

    void deleteAllByTaskId(TaskEntity taskEntity);
}
