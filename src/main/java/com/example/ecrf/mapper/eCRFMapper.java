package com.example.ecrf.mapper;

import com.example.ecrf.dto.RequesteCRF;
import com.example.ecrf.dto.ResponseeCRF;
import com.example.ecrf.model.eCRF;
import org.springframework.stereotype.Component;

@Component
public class eCRFMapper {

    public eCRF toeCRFFromRequest(RequesteCRF requesteCRF) {
        return eCRF.builder()
                .caseFamily(requesteCRF.getCaseFamily())
                .status(requesteCRF.getStatus())
                .created_At(requesteCRF.getCreated_At())
                .patId(requesteCRF.getPat_id())
                .filling_status(requesteCRF.getFilling_status())
                .build();
    }
    public eCRF toeCRFFromResponse(ResponseeCRF responseeCRF) {
        return eCRF.builder()
                .id(responseeCRF.getId())
                .caseFamily(responseeCRF.getCaseFamily())
                .status(responseeCRF.getStatus())
                .created_At(responseeCRF.getCreated_At())
                .patId(responseeCRF.getPat_id())
                .filling_status(responseeCRF.getFilling_status())
                .build();
    }
    public ResponseeCRF toResponse(eCRF eCRF) {
        return ResponseeCRF.builder()
                .id(eCRF.getId())
                .caseFamily(eCRF.getCaseFamily())
                .status(eCRF.getStatus())
                .created_At(eCRF.getCreated_At())
                .pat_id(eCRF.getPatId())
                .filling_status(eCRF.getFilling_status())
                .build();
    }
}
