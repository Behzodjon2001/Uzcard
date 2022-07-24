package com.company.entity;

import com.company.entity.template.BaseEntity;
import com.company.enums.TransferStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transfer")
public class TransferEntity extends BaseEntity {

    @Column(name = "from_card_id")
    private String fromCardId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_card_id", updatable = false, insertable = false)
    private CardEntity fromCard;

    @Column(name = "to_card_id")
    private String toCardId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_card_id", updatable = false, insertable = false)
    private CardEntity toCard;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "service_amount")
    private Long serviceAmount;

    @Column(name = "service_percentage")
    private String servicePercentage;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    @Column(name = "company_id")
    private String companyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", updatable = false, insertable = false)
    private CompanyEntity company;
}
