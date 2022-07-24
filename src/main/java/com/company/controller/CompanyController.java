package com.company.controller;

import com.company.dto.CompanyDTO;
import com.company.service.CompanyService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Company CRUD")
@Slf4j
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody CompanyDTO dto) {
        log.info("Request for create {}" , dto);
//        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        CompanyDTO companyDTO = companyService.create(dto);
        return ResponseEntity.ok().body(companyDTO);
    }

    @GetMapping("/adm/list")
    public ResponseEntity<?> list() {
        log.info("Request for list {}");
//        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        List<CompanyDTO> list = companyService.list();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/adm/update/{key}")
    public ResponseEntity<?> update(@PathVariable String key, @RequestBody CompanyDTO dto) {
        log.info("Request for update {}" , dto, key);
//        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        companyService.update(dto,key);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        log.info("Request for delete {}" , id);
//        HttpHeaderUtil.getId(request, ProfileRole.ADMIN);
        companyService.delete(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }
}
