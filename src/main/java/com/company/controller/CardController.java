package com.company.controller;

import com.company.dto.CardDTO;
import com.company.dto.ClientDTO;
import com.company.entity.CardEntity;
import com.company.service.CardService;
import com.company.util.CurrentUser;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Card CRUD for Bank")
@Slf4j
@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/bank/create")
    public ResponseEntity<?> create(@RequestBody CardDTO dto) {
        log.info("Request for create {}" , dto);
        CardDTO profileDTO = cardService.create(dto);
        return ResponseEntity.ok().body(profileDTO);
    }


    @PutMapping("/bank/phoneUpdate/{cardId}")
    public ResponseEntity<?> phoneUpdate(@PathVariable String cardId,@RequestBody CardDTO dto) {
        log.info("Request for update {}" , dto);
        cardService.phoneUpdate(cardId, dto);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @PutMapping("/bankPayment/statusUpdate/{cardId}")
    public ResponseEntity<?> statusUpdate(@PathVariable String cardId,@RequestBody CardDTO dto) {
        log.info("Request for update {}" , dto);
        cardService.statusUpdate(cardId, dto);
        return ResponseEntity.ok().body("Successfully updated");
    }


    @PostMapping("/adm/filter")
    public ResponseEntity<List<CardEntity>> filter(@RequestBody CardDTO dto) {
        log.info("Request for filter {}" , dto);
        List<CardEntity> response = cardService.filter(dto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/payment/getCardId/{cardId}")
    public ResponseEntity<?> getCardById(@PathVariable("cardId") String cardId) {
        log.info("Request for list {}" , cardId);
        CardDTO list = cardService.getCardById(cardId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/payment/getCardPhone/{cardPhone}")
    public ResponseEntity<?> getCardByPhone(@PathVariable("cardPhone") String cardPhone) {
        log.info("Request for list {}" , cardPhone);
        List<CardDTO> list = cardService.getCardByPhone(cardPhone);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/payment/getCardClientId/{cardClientId}")
    public ResponseEntity<?> getCardByClientId(@PathVariable("cardClientId") String cardClientId) {
        log.info("Request for list {}" , cardClientId);
        List<CardDTO> list = cardService.getCardByClientId(cardClientId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/payment/getCardNumber/{cardNumber}")
    public ResponseEntity<?> getCardByNumber(@PathVariable("cardNumber") Long cardNumber) {
        log.info("Request for list {}" , cardNumber);
        CardDTO list = cardService.getCardByNumber(cardNumber);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/payment/getCardBalanceNumber/{cardBalanceNumber}")
    public ResponseEntity<?> getCardByBalanceNumber(@PathVariable("cardBalanceNumber") Long cardBalanceNumber) {
        log.info("Request for list {}" , cardBalanceNumber);
        String list = cardService.getCardByBalanceNumber(cardBalanceNumber);
        return ResponseEntity.ok().body(list);
    }
}
