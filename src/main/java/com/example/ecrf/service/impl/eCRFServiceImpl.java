package com.example.ecrf.service.impl;

import com.example.ecrf.dto.RequesteCRF;
import com.example.ecrf.mapper.eCRFMapper;
import com.example.ecrf.model.eCRF;
import com.example.ecrf.repository.eCRFRepository;
import com.example.ecrf.service.eCRFService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        eCRF.setStatus("Draft");
        eCRF.setCaseFamily("eCRF compilation");
        eCRF.setCreated_At(formattedDate);
        eCRF.setFilling_status("L30");
        eCRF.setPat_id("6ool8962");
        eCRFREpository.save(mapper.toeCRFFromRequest(eCRF));
    }

    @Override
    public Page<eCRF> findByStatus(String path_Id, Pageable pageRequest) {

        return eCRFREpository.findAllByStatusIsNullOrDraftAndPatIdStartsWith(path_Id, pageRequest);
    }

    @Override
    public Page<eCRF> findBySend(String path_Id, Pageable pageRequest) {

        return eCRFREpository.findByStatusAndPatIdStartsWith(pageRequest, "sent", path_Id);
    }

    @Override
    public Page<eCRF> findByDelete(String path_Id, Pageable pageRequest) {

        return eCRFREpository.findByStatusAndPatIdStartsWith(pageRequest, "deleted", path_Id);
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
    public eCRF changeStatusSend(Long id) {
        eCRF byId = eCRFREpository.findById(id).get();
        byId.setStatus("sent");
        eCRFREpository.save(byId);
        return byId;
    }

    @Override
    public eCRF changeStatusDelete(Long id) {
        eCRF byId = eCRFREpository.findById(id).get();
        byId.setStatus("deleted");
        eCRFREpository.save(byId);
        return byId;
    }

    @Override
    public eCRF changeStatusDraft(Long id) {
        eCRF byId = eCRFREpository.findById(id).get();
        byId.setStatus("draft");
        eCRFREpository.save(byId);
        return byId;
    }
}
