package com.company.dto;

import com.company.enums.GeneralStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientDTO {
    private String id;

    private String name;

    private String surname;

    private String phone;

    private String middleName;

    private String passwordSeria;

    private String passwordNumber;

    private LocalDateTime createdDate;

    private GeneralStatus status;

    private Boolean visible;
}
