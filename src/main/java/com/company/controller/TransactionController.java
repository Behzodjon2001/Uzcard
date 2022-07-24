package com.company.controller;

import com.company.dto.transaction.TransactionCreateDTO;
import com.company.dto.transaction.TransactionDTO;
import com.company.service.TransactionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Transaction ")
@Slf4j
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

//    @PostMapping("/bankPaymentPayment/create")
//    public ResponseEntity<?> create(@RequestBody TransactionCreateDTO dto) {
//        log.info("Request for create {}" , dto);
//        TransactionDTO profileDTO = transactionService.create(dto);
//        return ResponseEntity.ok().body(profileDTO);
//    }


//    @PutMapping("/bankPayment/passswordUpdate")
//    public ResponseEntity<?> passswordUpdate(@RequestParam(value = "passwordNumber") String passwordNumber,
//                                             @RequestParam(value = "passwordSeria") String passwordSeria,
//                                             @RequestBody TransactionDTO dto) {
//        log.info("Request for update {}" , dto);
//        transactionService.update(passwordNumber,passwordSeria, dto);
//        return ResponseEntity.ok().body("Successfully updated");
//    }
//
//
    @GetMapping("/bankPayment/getTransaction")
    public ResponseEntity<?> getTransaction(@RequestParam(value = "cardId") String cardId,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "2") int size) {
        log.info("Request for list {}" , cardId);
        List<TransactionDTO> list = transactionService.getTransaction(cardId, size,page);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/bankPayment/getTransactionByCardNumber")
    public ResponseEntity<?> getTransactionByCardNumber(@RequestParam(value = "cardNumber") Long cardNumber,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "2") int size) {
        log.info("Request for list {}" , cardNumber);
        List<TransactionDTO> list = transactionService.getTransactionByCardNumber(cardNumber, size,page);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/bankPayment/getTransactionByProfileId")
    public ResponseEntity<?> getTransactionByProfileId(@RequestParam(value = "clientId") String clientId,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "2") int size) {
        log.info("Request for list {}" , clientId);
        List<TransactionDTO> list = transactionService.getTransactionByProfileId(clientId, size,page);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/bankPayment/getTransactionByPhone")
    public ResponseEntity<?> getTransactionByPhone(@RequestParam(value = "phone") String phone,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "2") int size) {
        log.info("Request for list {}" , phone);
        List<TransactionDTO> list = transactionService.getTransactionByPhone(phone, size,page);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/bankPayment/getCreditCardId")
    public ResponseEntity<?> getCreditCardId(@RequestParam(value = "cardId") String cardId,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "2") int size) {
        log.info("Request for list {}" , cardId);
        List<TransactionDTO> list = transactionService.getCreditCardId(cardId, size,page);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/bankPayment/getDebitCardId")
    public ResponseEntity<?> getDebitCardId(@RequestParam(value = "cardId") String cardId,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "2") int size) {
        log.info("Request for list {}" , cardId);
        List<TransactionDTO> list = transactionService.getDebitCardId(cardId, size,page);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/bankPayment/getDebitCreditByMonthly")
    public ResponseEntity<?> getDebitCreditByMonthly(@RequestParam(value = "month") String month,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "2") int size) {
        log.info("Request for list {}" , month);
        List<TransactionDTO> list = transactionService.getDebitCreditByMonthly(month, size,page);
        return ResponseEntity.ok().body(list);
    }
//
//    @PostMapping("/bankPayment/filter")
//    public ResponseEntity<List<TransactionDTO>> filter(@RequestBody TransactionDTO dto) {
//        log.info("Request for filter {}" , dto);
//        List<TransactionDTO> response = transactionService.filter(dto);
//        return ResponseEntity.ok().body(response);
//    }
}
