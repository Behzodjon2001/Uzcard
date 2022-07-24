package com.company.dto;

import com.company.enums.GeneralRole;
import com.company.enums.GeneralStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileDTO {
    private String id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private GeneralStatus status;

    private LocalDateTime createdDate;

    private GeneralRole role;


    private Boolean visible;
}
