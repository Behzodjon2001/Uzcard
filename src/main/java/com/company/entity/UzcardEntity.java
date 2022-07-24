package com.company.entity;

import com.company.entity.template.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "uzcard")
public class UzcardEntity extends BaseEntity {

}
