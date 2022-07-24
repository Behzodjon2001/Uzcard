package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.service.ProfileService;
import com.company.util.CurrentUser;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Profile CRUD for Admin")
@Slf4j
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody ProfileDTO dto) {
        log.info("Request for create {}" , dto);
        ProfileDTO profileDTO = profileService.create(dto);
        return ResponseEntity.ok().body(profileDTO);
    }

    @PutMapping("/adm/passswordUpdate/{username}")
    public ResponseEntity<?> passswordUpdate(@PathVariable("username") String username,@RequestBody ProfileDTO dto) {
        log.info("Request for update {}" , dto);
        profileService.update(username, dto);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @PostMapping("/adm/filter")
    public ResponseEntity<List<ProfileEntity>> filter(@RequestBody ProfileDTO dto) {
        log.info("Request for filter {}" , dto);
        List<ProfileEntity> response = profileService.filter(dto);
        return ResponseEntity.ok().body(response);
    }
}
