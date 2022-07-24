package com.company.service;

import com.company.dto.ClientDTO;
import com.company.dto.transaction.TransactionCreateDTO;
import com.company.dto.transaction.TransactionDTO;
import com.company.dto.transfer.TransferCreateDTO;
import com.company.dto.transfer.TransferDTO;
import com.company.dto.transfer.TransferResponseDTO;
import com.company.entity.*;
import com.company.enums.GeneralStatus;
import com.company.enums.TransactionStatus;
import com.company.enums.TransactionType;
import com.company.enums.TransferStatus;
import com.company.exception.ItemNotFoundException;
import com.company.mapper.TransactionInfo;
import com.company.repository.CardRepository;
import com.company.repository.ClientRepository;
import com.company.repository.TransactionRepository;
import com.company.repository.TransferRepository;
import com.company.repository.custome.CustomeTransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CustomeTransferRepository customeTransferRepository;

    private final double uzCardServicePercentage = 0.005;

    public TransferResponseDTO create(TransferCreateDTO transferDTO) {

        CardEntity fromCard = cardService.get(transferDTO.getFromCard());
        CardEntity toCard = cardService.get(transferDTO.getToCard());
        CompanyEntity company = companyService.get(transferDTO.getCompanyId());

        if (!fromCard.getStatus().equals(GeneralStatus.ACTIVE)) {
            return new TransferResponseDTO("failed", "Card not active");
        }

        if (!toCard.getStatus().equals(GeneralStatus.ACTIVE)) {
            return new TransferResponseDTO("failed", "Card not active");
        }

        // 10,000
        //  company.getServicePercentage()  0.3
        //  0.4 - uzcard
        double service_percentage = uzCardServicePercentage + company.getServicePercentage(); // 0.7
        double service_amount = transferDTO.getAmount() * service_percentage; // 70
        double total_amount = transferDTO.getAmount() + service_amount; // 10,070

        if (fromCard.getBalance() < total_amount) {
            return new TransferResponseDTO("failed", "Not enough money");
        }

        TransferEntity entity = new TransferEntity();
        entity.setAmount(transferDTO.getAmount());
        entity.setFromCardId(fromCard.getId());
        entity.setToCardId(transferDTO.getToCard());
        entity.setServiceAmount((long) service_amount);
        entity.setTotalAmount((long) total_amount);
        entity.setStatus(TransferStatus.IN_PROGRESS);
        entity.setCompanyId(transferDTO.getCompanyId());
        entity.setServicePercentage(String.valueOf(service_percentage));
        // ..........
        transferRepository.save(entity);

        TransferDTO dto = new TransferDTO();
        // id, card ,... total_amount
        dto.setFromCardId(entity.getFromCardId());
        dto.setToCardId(entity.getToCardId());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setServicePercentage(entity.getServicePercentage());
        dto.setServiceAmount(entity.getServiceAmount());
        dto.setCompanyId(entity.getCompanyId());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setAmount(entity.getAmount());

        return new TransferResponseDTO("success", "", dto);
    }

    public TransactionDTO finishTransfer(TransactionCreateDTO dto) {
        Optional<TransferEntity> optional = transferRepository.findById(dto.getTransferId());
        TransferEntity transferEntity = optional.get();

        TransactionsEntity entity1 = new TransactionsEntity();
        entity1.setCardId(transferEntity.getFromCardId());
        entity1.setAmount(transferEntity.getTotalAmount());
        entity1.setTransferId(dto.getTransferId());
        entity1.setTransactionType(TransactionType.CREDIT);
        entity1.setStatus(TransactionStatus.SUCCESS);
        entity1.setCreatedDate(LocalDateTime.now());

        transactionRepository.save(entity1);


        TransactionsEntity entity2 = new TransactionsEntity();
        entity2.setCardId(transferEntity.getToCardId());
        entity2.setAmount(transferEntity.getAmount());
        entity2.setTransferId(dto.getTransferId());
        entity2.setTransactionType(TransactionType.DEBIT);
        entity2.setStatus(TransactionStatus.SUCCESS);
        entity2.setCreatedDate(LocalDateTime.now());

        transactionRepository.save(entity2);

//        Optional<CardEntity> cardEntity = cardRepository.findById("4028d6d18220dcb9018220e013370000");
//        CardEntity cardUzCard = cardEntity.get();

        //company ... Payment
        TransactionsEntity entity3 = new TransactionsEntity();
//        entity3.setCardId("8a8a85f18225613801822561eda90000");
        Optional<CardEntity> byCompanyId = cardRepository.findByCompanyId(transferEntity.getCompanyId());
        CardEntity paymentCard = byCompanyId.get();
        entity3.setCardId(paymentCard.getId());
        entity3.setAmount(transferEntity.getServiceAmount()/2);
        entity3.setTransferId(dto.getTransferId());
        entity3.setTransactionType(TransactionType.DEBIT);
        entity3.setStatus(TransactionStatus.SUCCESS);
        entity3.setCreatedDate(LocalDateTime.now());

        transactionRepository.save(entity3);

        //uz_card ...profile
        TransactionsEntity entity4 = new TransactionsEntity();
        entity4.setCardId("8a8a85f1822589c50182258c12890004");
        entity4.setAmount(transferEntity.getServiceAmount()/2);
        entity4.setTransferId(dto.getTransferId());
        entity4.setTransactionType(TransactionType.DEBIT);
        entity4.setStatus(TransactionStatus.SUCCESS);
        entity4.setCreatedDate(LocalDateTime.now());

        transactionRepository.save(entity4);

        // giver credit
        Optional<CardEntity> creditOwnOptional = cardRepository.findById(transferEntity.getFromCardId());
        CardEntity credirOwner = creditOwnOptional.get();
        credirOwner.setBalance(credirOwner.getBalance() - transferEntity.getTotalAmount());
        cardRepository.save(credirOwner);

        // receiver debit
        Optional<CardEntity> debitOwnOptional = cardRepository.findById(transferEntity.getToCardId());
        CardEntity debitOwner = debitOwnOptional.get();
        debitOwner.setBalance(debitOwner.getBalance() + transferEntity.getAmount());
        cardRepository.save(credirOwner);

        //Payment
        Optional<CardEntity> paymentOwnOptional = cardRepository.findById("8a8a85f18225613801822561eda90000");
        CardEntity paymentOwner = paymentOwnOptional.get();
        paymentOwner.setBalance(paymentOwner.getBalance() + transferEntity.getServiceAmount()/2);
        cardRepository.save(credirOwner);

        //Uzcard
        Optional<CardEntity> uzcardOwnOptional = cardRepository.findById("8a8a85f1822589c50182258c12890004");
        CardEntity uzcardOwner = uzcardOwnOptional.get();
        uzcardOwner.setBalance(uzcardOwner.getBalance() + transferEntity.getServiceAmount()/2);
        cardRepository.save(credirOwner);

        Optional<TransferEntity> byId = transferRepository.findById(dto.getTransferId());
        TransferEntity transferStatus = byId.get();
        transferStatus.setStatus(TransferStatus.SUCCESS);

        TransactionDTO dto1 = new TransactionDTO();
        dto1.setId(entity1.getId());
        dto1.setCreatedDate(entity1.getCreatedDate());
        dto1.setTransactionType(entity1.getTransactionType());
        dto1.setStatus(entity1.getStatus());
        dto1.setCardId(entity1.getCardId());
        dto1.setAmount(entity1.getAmount());
        dto1.setTransferId(entity1.getTransferId());
        return dto1;


        // ...
        // 1. fromCard - CREDIT
        // 2. TOCARD - DEBIT
        // 3. card_id (PAYMENT,BANK) - DEBIT
        // 4. card_id  (UZ_CARD) - DEBIT

        // FROM_CARD_UPDATE
        // TO_CARD_UPDATE
        // Company_CARD_UPDATE
        // Uzcard_CARD_UPDATE

        // Transfer status update
    }

    public void statusUpdate(String transferId) {

        Optional<TransferEntity> profile = transferRepository.findById(transferId);

        if (profile.isEmpty()) {
            log.error("Client Not Found {}", transferId);
            throw new ItemNotFoundException("Client Not Found ");
        }


        TransferEntity entity = profile.get();
        if (entity.getStatus().equals(TransferStatus.SUCCESS)){
            entity.setStatus(TransferStatus.FAILED);
        } else {
            entity.setStatus(TransferStatus.SUCCESS);
        }

        transferRepository.save(entity);
    }

    public List<TransactionsEntity> getTransfer(String byId) {
        List<TransactionInfo> transactionsEntities = transferRepository.getTransfer(byId);
        List<TransactionsEntity> entityList = new ArrayList<>();
        for (TransactionInfo entity: transactionsEntities) {
            TransactionsEntity dto = new TransactionsEntity();
            dto.setId(entity.getTransferId());
            dto.setAmount(entity.getAmount());

            ClientEntity client = new ClientEntity();
            client.setId(entity.getClientIdC());
            client.setName(entity.getNameC());
            client.setSurname(entity.getSurnameC());

            ClientEntity client1 = new ClientEntity();
            client1.setId(entity.getClientIdD());
            client1.setName(entity.getNameD());
            client1.setSurname(entity.getSurnameD());

            CardEntity card = new CardEntity();
            card.setId(entity.getCardIdC());
            card.setNumber(entity.getNumberC());
            card.setPhone(entity.getPhoneC());
            card.setClient(client);

            CardEntity card1 = new CardEntity();
            card1.setId(entity.getCardIdD());
            card1.setNumber(entity.getNumberD());
            card1.setPhone(entity.getPhoneD());
            card1.setClient(client1);

            TransferEntity entity1 = new TransferEntity();
            entity1.setFromCard(card);
            entity1.setToCard(card1);
            entity1.setCreatedDate(entity.getCreationDate());
            entity1.setStatus(entity.getStatus());

            dto.setTransfer(entity1);
            dto.setCreatedDate(entity.getCreationDate());

            entityList.add(dto);
        }

        return entityList;
    }

    public List<TransferEntity> filter(TransferDTO dto) {
        return  customeTransferRepository.filter(dto);
    }
}
