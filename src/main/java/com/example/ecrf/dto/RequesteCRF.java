package com.example.ecrf.dto;

import com.example.ecrf.model.eCRFStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RequesteCRF {
    private String status;
    private String caseFamily;
    private String created_At;
    private String filling_status;
    private String pat_id;
}

