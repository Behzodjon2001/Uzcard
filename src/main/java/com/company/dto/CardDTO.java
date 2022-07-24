package com.company.dto;

import com.company.entity.ClientEntity;
import com.company.entity.CompanyEntity;
import com.company.enums.GeneralStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CardDTO {
    private String id;

    private Long number;

    private String hiddenNumber;

    private Date expiredDate;

    private String phone;

    private GeneralStatus status;

    private Long balance;

    private LocalDateTime createdDate;

    private String clientId;

    private String companyId;
}
