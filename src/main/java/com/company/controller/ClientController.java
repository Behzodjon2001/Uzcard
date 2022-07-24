package com.company.controller;

import com.company.dto.ClientDTO;
import com.company.entity.ClientEntity;
import com.company.service.ClientService;
import com.company.util.CurrentUser;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Client CRUD for Bank")
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/bank/create")
    public ResponseEntity<?> create(@RequestBody ClientDTO dto) {
        log.info("Request for create {}" , dto);
        ClientDTO profileDTO = clientService.create(dto);
        return ResponseEntity.ok().body(profileDTO);
    }


    @PutMapping("/bank/passswordUpdate")
    public ResponseEntity<?> passswordUpdate(@RequestParam(value = "passwordNumber") String passwordNumber,
                                             @RequestParam(value = "passwordSeria") String passwordSeria,
                                             @RequestBody ClientDTO dto) {
        log.info("Request for update {}" , dto);
        clientService.update(passwordNumber,passwordSeria, dto);
        return ResponseEntity.ok().body("Successfully updated");
    }


    @GetMapping("/bankAdmin/getClient/{clientId}")
    public ResponseEntity<?> getClient(@PathVariable("clientId") String clientId) {
        log.info("Request for list {}" , clientId);
        ClientDTO list = clientService.getClient(clientId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/bankAdmin/filter")
    public ResponseEntity<List<ClientEntity>> filter(@RequestBody ClientDTO dto) {
        log.info("Request for filter {}" , dto);
        List<ClientEntity> response = clientService.filter(dto);
        return ResponseEntity.ok().body(response);
    }
}
