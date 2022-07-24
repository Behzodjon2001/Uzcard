package com.company.repository;

import com.company.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, String> {
    Optional<CardEntity> findByNumber(Long number);

    List<CardEntity> findAllByPhone(String cardId);

    List<CardEntity> findAllByClientId(String cardId);

    Optional<CardEntity> findByCompanyId(String companyId);
}
