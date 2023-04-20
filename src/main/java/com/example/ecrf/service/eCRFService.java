package com.example.ecrf.service;

import com.example.ecrf.dto.RequesteCRF;
import com.example.ecrf.model.eCRF;
import com.example.ecrf.model.eCRFStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface eCRFService {
    void create(RequesteCRF eCRF) throws ParseException;

    Page<eCRF> findByStatus( String path_Id, Pageable pageable);

    Page<eCRF> findByDelete( String path_Id,Pageable pageable);

    Page<eCRF> findBySend( String path_Id, Pageable pageable);

    List<eCRF> alleCRFEs();

    Page<eCRF> findAlleCRFEs(Pageable pageRequest);

    eCRF changeStatusSend(Long id);

    eCRF changeStatusDelete(Long id);
    eCRF changeStatusDraft(Long id);
}
