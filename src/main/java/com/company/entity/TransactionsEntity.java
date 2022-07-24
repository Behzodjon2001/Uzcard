package com.company.entity;

import com.company.entity.template.BaseEntity;
import com.company.enums.TransactionStatus;
import com.company.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class TransactionsEntity extends BaseEntity {

    @Column(name = "card_id")
    private String cardId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", updatable = false, insertable = false)
    private CardEntity card;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "transfer_id")
    private String transferId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transfer_id", updatable = false, insertable = false)
    private TransferEntity transfer;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}
