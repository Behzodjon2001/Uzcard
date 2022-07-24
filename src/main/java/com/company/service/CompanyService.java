package com.company.service;

import com.company.dto.CompanyDTO;
import com.company.entity.CompanyEntity;
import com.company.exception.BadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.CompanyRepository;
import com.company.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity get(Integer id) {
        return companyRepository.findByIdAndVisible(id, true).orElseThrow(() -> {
            log.error("Profile Not Found {}", id);
            throw new ItemNotFoundException("Profile not found");
        });
    }

    public CompanyDTO create(CompanyDTO dto) {
        Optional<CompanyEntity> optional = companyRepository.findByUsername(dto.getUsername());
        if (optional.isPresent()) {
            log.error("This category already exist {}" , dto);
            throw new BadRequestException("This category already exist");
        }
        CompanyEntity entity = new CompanyEntity();
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setAddress(dto.getAddress());
        entity.setContact(dto.getContact());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setRole(dto.getRole());
        entity.setVisible(Boolean.TRUE);
        entity.setServicePercentage(0.005);
        companyRepository.save(entity);

        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;
    }

    public List<CompanyDTO> list() {
        Iterable<CompanyEntity> all = companyRepository.findAll();
        List<CompanyDTO> list = new LinkedList<>();
        for (CompanyEntity entity : all) {
            CompanyDTO dto = new CompanyDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());

            dto.setCreatedDate(entity.getCreatedDate());

            list.add(dto);
        }
        return list;
    }

    public void update(CompanyDTO dto,String key) {
        CompanyEntity entity = get(key);
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setAddress(dto.getAddress());
        entity.setContact(dto.getContact());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setRole(dto.getRole());
        companyRepository.save(entity);
    }

    public CompanyEntity get(String key) {
        return companyRepository.findById(key).orElseThrow(() -> {
            log.error("This category not found {}" , key);
            throw new ItemNotFoundException("This category not found");
        });
    }

    public void delete(String id) {
        Optional<CompanyEntity> optional = companyRepository.findById(id);
        if (optional.isEmpty()) {
            log.error("This category not found {}" , id);
            throw new BadRequestException("This category not found");
        }
        CompanyEntity entity = optional.get();
        entity.setVisible(false);
        companyRepository.save(entity);
    }
}
