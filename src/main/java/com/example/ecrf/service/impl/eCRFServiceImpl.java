package com.example.ecrf.service.impl;

import com.example.ecrf.dto.RequesteCRF;
import com.example.ecrf.dto.ResponseeCRF;
import com.example.ecrf.mapper.eCRFMapper;
import com.example.ecrf.model.eCRF;
import com.example.ecrf.model.eCRFStatus;
import com.example.ecrf.repository.eCRFRepository;
import com.example.ecrf.service.eCRFService;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class eCRFServiceImpl implements eCRFService {
    private final eCRFRepository eCRFREpository;
    private final eCRFMapper mapper;

    @Override
    public void create(RequesteCRF eCRF) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date(); // create a new Date object with the current date/time
        String formattedDate = sdf.format(now);
        eCRF.setStatus(eCRFStatus.DRAFT);
        eCRF.setCaseFamily("eCRF compilation");
        eCRF.setCreated_At(formattedDate);
        eCRF.setFilling_status("120");
        eCRF.setPat_id("4L148962");
        eCRFREpository.save(mapper.toeCRFFromRequest(eCRF));
    }

    @Override
    public  Page<eCRF> findByStatus(String path_Id, Pageable pageRequest) {

        return eCRFREpository.findByStatusAndPatIdStartsWith(pageRequest,eCRFStatus.DRAFT,path_Id);
    }

    @Override
    public  Page<eCRF> findBySend(String path_Id, Pageable pageRequest) {

        return eCRFREpository.findByStatusAndPatIdStartsWith(pageRequest,eCRFStatus.SEND,path_Id);
    }
    @Override
    public  Page<eCRF> findByDelete(  String path_Id,Pageable pageRequest) {

        return eCRFREpository.findByStatusAndPatIdStartsWith(pageRequest,eCRFStatus.DELETED,path_Id);
    }

    @Override
    public List<eCRF> alleCRFEs() {
        return eCRFREpository.findAll();
    }

    @Override
    public Page<eCRF> findAlleCRFEs(Pageable pageRequest) {
        return eCRFREpository.findAll(pageRequest);
    }

    @Override
    public eCRF changeStatusSend( Long id) {
        eCRF byId = eCRFREpository.findById(id).get();
        byId.setStatus(eCRFStatus.SEND);
        eCRFREpository.save(byId);
        return byId;
    }

    @Override
    public eCRF changeStatusDelete( Long id) {
        eCRF byId = eCRFREpository.findById(id).get();
        byId.setStatus(eCRFStatus.DELETED);
        eCRFREpository.save(byId);
        return byId;
    }

    @Override
    public eCRF changeStatusDraft(Long id) {
        eCRF byId = eCRFREpository.findById(id).get();
        byId.setStatus(eCRFStatus.DRAFT);
        eCRFREpository.save(byId);
        return byId;    }
}
