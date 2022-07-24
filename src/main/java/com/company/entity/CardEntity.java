package com.company.entity;

import com.company.entity.template.BaseEntity;
import com.company.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "card")
public class CardEntity extends BaseEntity {

    @Column(nullable = false)
    private Long number;

    @Column()
    private String hiddenNumber;

    @Column(name = "expired_date")
    private Date expiredDate;

    @Column
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    private GeneralStatus status=GeneralStatus.ACTIVE;

    @Column
    private Long balance;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "client_id")
    private String clientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", updatable = false, insertable = false)
    private ClientEntity client;

    @Column(name = "company_id")
    private String companyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", updatable = false, insertable = false)
    private CompanyEntity company;
}
