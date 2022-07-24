package com.company.mapper;

import com.company.enums.TransactionType;
import com.company.enums.TransferStatus;

import java.time.LocalDateTime;

public interface TransactionInfo {
    String getTransferId();
    String getToCard();
    Long getAmount();
    String getFromCard();
    LocalDateTime getCreationDate();
    TransferStatus getStatus();
    String getCardIdC();
    Long getNumberC();
    String getPhoneC();
    String getClientIdC();
    String getNameC();
    String getSurnameC();
    String getCardIdD();
    Long getNumberD();
    String getPhoneD();
    String getClientIdD();
    String getNameD();
    String getSurnameD();
}
