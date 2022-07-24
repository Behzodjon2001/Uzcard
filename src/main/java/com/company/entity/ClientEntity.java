package com.company.entity;

import com.company.entity.template.BaseEntity;
import com.company.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "client",uniqueConstraints={@UniqueConstraint(columnNames = {"passwordSeria", "passwordNumber"})})
public class ClientEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column
    private String surname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String passwordSeria;

    @Column(nullable = false)
    private String passwordNumber;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    @Enumerated(EnumType.STRING)
    private GeneralStatus status=GeneralStatus.ACTIVE;

    @Column
    private Boolean visible=true;

}
