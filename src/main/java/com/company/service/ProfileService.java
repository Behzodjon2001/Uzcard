package com.company.service;

import com.company.config.CustomUserDetails;
import com.company.dto.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.GeneralRole;
import com.company.enums.GeneralStatus;
import com.company.exception.BadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import com.company.repository.custome.CustomeProfileRepository;
import com.company.util.CurrentUser;
import com.company.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CustomeProfileRepository customeProfileRepository;

    public ProfileEntity get(String id) {
        return profileRepository.findByIdAndVisible(id, true).orElseThrow(() -> {
            log.error("Company Not Found {}", id);
            throw new ItemNotFoundException("Company not found");
        });
    }

    public ProfileDTO create(ProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByUsername(dto.getUsername());
        if (optional.isPresent()) {
            log.error("Profile already exists {}", dto);
            throw new BadRequestException("Profile already exists");
        }
        GeneralRole role = checkRole(dto.getRole().name());

        ProfileEntity entity = new ProfileEntity();
        entity.setRole(role);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername("uz_card_" + dto.getUsername());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);

        profileRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public void update(String pId, ProfileDTO dto) {

        Optional<ProfileEntity> profile = profileRepository.findByUsername(pId);

        if (profile.isEmpty()) {
            log.error("Profile Not Found {}", dto);
            throw new ItemNotFoundException("Profile Not Found ");
        }
        GeneralRole role = checkRole(dto.getRole().name());

        ProfileEntity entity = profile.get();
        entity.setRole(role);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername(dto.getUsername());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setStatus(GeneralStatus.ACTIVE);
        profileRepository.save(entity);
    }


    public CustomUserDetails getProfile() {

        CustomUserDetails user = CurrentUser.getCurrentUser();
        return user;
    }


    private GeneralRole checkRole(String role) {
        try {
            return GeneralRole.valueOf(role);
        } catch (RuntimeException e) {
            log.error("Role is wrong {}", role);
            throw new BadRequestException("Role is wrong");
        }
    }

    public List<ProfileEntity> filter(ProfileDTO dto) {
        return customeProfileRepository.filter(dto);
    }
}
