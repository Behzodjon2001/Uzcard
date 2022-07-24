package com.company.dto.transaction;

import com.company.enums.TransactionStatus;
import com.company.enums.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionCreateDTO {
    private String id;

    private String transferId;
}
