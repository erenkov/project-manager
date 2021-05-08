package com.developing.simbir_product.repository;

import com.developing.simbir_product.entity.ReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReleaseRepository extends JpaRepository<ReleaseEntity, UUID> {
    Optional<ReleaseEntity> findByName(String name);

    @Query("SELECT r FROM ReleaseEntity r WHERE r.projectId = :projectId AND r.finishDate = (SELECT MAX(r2.finishDate) FROM ReleaseEntity r2)")
    Optional<ReleaseEntity> getCurrentRelease(UUID projectId);
}
