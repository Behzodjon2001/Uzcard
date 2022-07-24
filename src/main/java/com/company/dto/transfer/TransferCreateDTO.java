package com.company.dto.transfer;

import lombok.Data;

@Data
public class TransferCreateDTO {
    private String fromCard;
    private String toCard;
    private Long amount;
    private String companyId;
}
