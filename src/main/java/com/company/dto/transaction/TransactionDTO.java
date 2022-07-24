package com.company.dto.transaction;

import com.company.entity.CardEntity;
import com.company.entity.TransferEntity;
import com.company.enums.TransactionStatus;
import com.company.enums.TransactionType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private String id;

    private String cardId;

    private Long amount;

    private TransactionType transactionType;

    private String transferId;

    private LocalDateTime createdDate;

    private TransactionStatus status;
}
