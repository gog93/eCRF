package com.example.ecrf.repository;

import com.example.ecrf.model.eCRF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface eCRFRepository extends JpaRepository<eCRF, Long> {
    Page<eCRF> findByStatus(String status, Pageable pageable);

    Optional<eCRF> findById(Long id);

    @Query("SELECT e FROM eCRF e WHERE (LOWER(e.status) IS NULL OR LOWER(e.status) = 'draft') AND e.patId LIKE :patIdStartsWith%")
    Page<eCRF> findAllByStatusIsNullOrDraftAndPatIdStartsWith(@Param("patIdStartsWith") String patIdStartsWith, Pageable pageable);

    Page<eCRF> findByStatusAndPatIdStartsWith(Pageable pageable, String status, String patIdStartsWith);

}
