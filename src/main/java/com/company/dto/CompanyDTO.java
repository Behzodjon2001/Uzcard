package com.company.dto;

import com.company.enums.GeneralRole;
import com.company.enums.GeneralStatus;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class CompanyDTO {
    private String id;

    private String name;

    private String username;

    private String password;

    private String address;

    private String contact;

    private LocalDateTime createdDate = LocalDateTime.now();

    private GeneralRole role;

    private Boolean visible;

    private GeneralStatus status;
}
