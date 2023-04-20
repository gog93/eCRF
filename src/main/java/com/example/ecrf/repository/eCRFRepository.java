package com.example.ecrf.repository;

import com.example.ecrf.model.eCRF;
import com.example.ecrf.model.eCRFStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface eCRFRepository extends JpaRepository<eCRF, Long> {
    Page<eCRF> findByStatus(eCRFStatus status, Pageable pageable);
   Optional<eCRF> findById(Long id);
    Page<eCRF> findByStatusAndPatIdStartsWith(Pageable pageable, eCRFStatus status, String patIdStartsWith);

}
