package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends  JpaRepository<UserEntity, UUID>{
}
