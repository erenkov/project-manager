package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.UserTaskHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTaskHistoryRepository extends  JpaRepository<UserTaskHistoryEntity, UserTaskId>{

    Optional<UserTaskHistoryEntity> findByUserIdAndTaskId(UUID userId, UUID taskId);//todo добавить 3 поле

    void deleteByUserIdAndTaskId(UUID userId, UUID taskId); //todo добавить 3 поле
}
