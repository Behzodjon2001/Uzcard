package com.company;

import com.company.entity.ProfileEntity;
import com.company.enums.GeneralRole;
import com.company.enums.GeneralStatus;
import com.company.repository.ProfileRepository;
import com.company.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class UzcardApplicationTests {
    @Autowired
    private ProfileRepository profileRepository;

    @Test
    void contextLoads() {
        ProfileEntity entity = new ProfileEntity();
        entity.setRole(GeneralRole.valueOf("ROLE_ADMIN"));
        entity.setName("Behzodjon");
        entity.setSurname("Malikov");
        entity.setUsername("Behzodjon_M");
        entity.setPassword(MD5Util.getMd5("1255"));
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);

        profileRepository.save(entity);
    }

}
