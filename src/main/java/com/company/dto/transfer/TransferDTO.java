package com.company.dto.transfer;

import com.company.enums.TransferStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransferDTO {
    private String Id;

    private String fromCardId;

    private String toCardId;

    private Long totalAmount;

    private Long amount;

    private Long serviceAmount;

    private String servicePercentage;

    private LocalDateTime createdDate;

    private TransferStatus status;

    private String companyId;
}
