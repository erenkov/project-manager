package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends  JpaRepository<UserEntity, UUID>{

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByUserNumber(Integer userNumber);

    List<UserEntity> findByTeamId(TeamEntity teamEntity);
}
