package com.company.controller;

import com.company.dto.transaction.TransactionCreateDTO;
import com.company.dto.transaction.TransactionDTO;
import com.company.dto.transfer.TransferCreateDTO;
import com.company.dto.transfer.TransferDTO;
import com.company.dto.transfer.TransferResponseDTO;
import com.company.entity.TransactionsEntity;
import com.company.entity.TransferEntity;
import com.company.service.TransactionService;
import com.company.service.TransferService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Api(tags = "Transfer ")
@Slf4j
@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private TransferService transferService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/public/create")
    public ResponseEntity<?> create(@RequestBody TransferCreateDTO dto) {
        log.info("Request for create {}" , dto);
        TransferResponseDTO profileDTO = transferService.create(dto);
        return ResponseEntity.ok().body(profileDTO);
    }


    @PutMapping("/public/statusUpdate/{transferId}")
    public ResponseEntity<?> statusUpdate(@PathVariable("transferId") String transferId) {
        log.info("Request for update {}" , transferId);
        transferService.statusUpdate(transferId);
        return ResponseEntity.ok().body("Successfully updated");
    }


    @PostMapping("/bankPaymentPayment/create")
    public ResponseEntity<?> create(@RequestBody TransactionCreateDTO dto) {
        log.info("Request for create {}" , dto);
        TransactionDTO profileDTO = transferService.finishTransfer(dto);
        return ResponseEntity.ok().body(profileDTO);
    }


    @GetMapping("/public/getTransfer/{byId}")
    public ResponseEntity<?> getTransfer(@PathVariable("byId") String byId) {
        log.info("Request for list {}" , byId);
        List<TransactionsEntity> list = transferService.getTransfer(byId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/adm/filter")
    public ResponseEntity<List<TransferEntity>> filter(@RequestBody TransferDTO dto) {
        log.info("Request for filter {}" , dto);
        List<TransferEntity> response = transferService.filter(dto);
        return ResponseEntity.ok().body(response);
    }
}
