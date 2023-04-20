package com.example.ecrf.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "Registration1_details")
public class eCRF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private eCRFStatus status;
    private String caseFamily;
    private String created_At;
    private String filling_status;
    private String patId;
}
