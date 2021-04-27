package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.UserTaskHistoryEntity;
import com.developing.simbir_product.entity.UserTaskId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTaskHistoryRepository extends  JpaRepository<UserTaskHistoryEntity, UserTaskId>{

}
