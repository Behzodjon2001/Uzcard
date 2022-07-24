package com.company.repository;

import com.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

    Optional<CompanyEntity> findByIdAndVisible(Integer id, boolean b);

    Optional<CompanyEntity> findByUsername(String username);

    Optional<CompanyEntity> findByName(String name);

}
