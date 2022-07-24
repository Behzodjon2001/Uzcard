package com.company.service;

import com.company.config.CustomUserDetails;
import com.company.dto.CardDTO;
import com.company.dto.transaction.TransactionCreateDTO;
import com.company.dto.transaction.TransactionDTO;
import com.company.entity.CardEntity;
import com.company.entity.TransactionsEntity;
import com.company.entity.TransferEntity;
import com.company.enums.GeneralStatus;
import com.company.enums.TransactionStatus;
import com.company.enums.TransactionType;
import com.company.exception.BadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.*;
import com.company.repository.custome.CustomeTransactionRepository;
import com.company.util.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
//    @Autowired
//    private CustomeTransactionRepository customTransactionRepository;
//    @Autowired
//    private CardRepository  cardRepository;
//    @Autowired
//    private TransferRepository transferRepository;


//    public TransactionDTO create(TransactionCreateDTO dto) {
//        Optional<TransferEntity> optional = transferRepository.findById(dto.getTransferId());
//        TransferEntity transferEntity = optional.get();
//
//        TransactionsEntity entity1 = new TransactionsEntity();
//        entity1.setCardId(transferEntity.getFromCardId());
//        entity1.setAmount(transferEntity.getTotalAmount());
//        entity1.setTransferId(dto.getTransferId());
//        entity1.setTransactionType(TransactionType.CREDIT);
//        entity1.setStatus(TransactionStatus.SUCCESS);
//        entity1.setCreatedDate(LocalDateTime.now());
//
//        transactionRepository.save(entity1);
//
//
//        TransactionsEntity entity2 = new TransactionsEntity();
//        entity2.setCardId(transferEntity.getToCardId());
//        entity2.setAmount(transferEntity.getAmount());
//        entity2.setTransferId(dto.getTransferId());
//        entity2.setTransactionType(TransactionType.DEBIT);
//        entity2.setStatus(TransactionStatus.SUCCESS);
//        entity2.setCreatedDate(LocalDateTime.now());
//
//        transactionRepository.save(entity2);
//
////        Optional<CardEntity> cardEntity = cardRepository.findById("4028d6d18220dcb9018220e013370000");
////        CardEntity cardUzCard = cardEntity.get();
//
//        //company ... Payment
//        TransactionsEntity entity3 = new TransactionsEntity();
//        entity3.setCardId("8a8a85f18225613801822561eda90000");
//        entity3.setAmount(transferEntity.getServiceAmount()/2);
//        entity3.setTransferId(dto.getTransferId());
//        entity3.setTransactionType(TransactionType.DEBIT);
//        entity3.setStatus(TransactionStatus.SUCCESS);
//        entity3.setCreatedDate(LocalDateTime.now());
//
//        transactionRepository.save(entity3);
//
//        //uz_card ...profile
//        TransactionsEntity entity4 = new TransactionsEntity();
//        entity4.setCardId("8a8a85f1822589c50182258c12890004");
//        entity4.setAmount(transferEntity.getServiceAmount()/2);
//        entity4.setTransferId(dto.getTransferId());
//        entity4.setTransactionType(TransactionType.DEBIT);
//        entity4.setStatus(TransactionStatus.SUCCESS);
//        entity4.setCreatedDate(LocalDateTime.now());
//
//        transactionRepository.save(entity4);
//
//        TransactionDTO dto1 = new TransactionDTO();
//        dto1.setId(entity1.getId());
//        dto1.setCreatedDate(entity1.getCreatedDate());
//        dto1.setTransactionType(entity1.getTransactionType());
//        dto1.setStatus(entity1.getStatus());
//        dto1.setCardId(entity1.getCardId());
//        dto1.setAmount(entity1.getAmount());
//        dto1.setTransferId(entity1.getTransferId());
//        return dto1;
//    }


//    public void update(String passwordNumber, String passwordSeria, TransactionDTO dto) {
//
//        Optional<TransactionsEntity> profile = transactionRepository.
//                findByPasswordNumberAndPasswordSeria(passwordNumber,passwordSeria);
//
//        if (profile.isEmpty()) {
//            log.error("Transaction Not Found {}", dto);
//            throw new ItemNotFoundException("Transaction Not Found ");
//        }
//
//
//        TransactionsEntity entity = profile.get();
//        entity.setName(dto.getName());
//        entity.setSurname(dto.getSurname());
//        entity.setMiddleName(dto.getMiddleName());
//        entity.setPhone(dto.getPhone());
//        entity.setPasswordNumber(dto.getPasswordNumber());
//        entity.setPasswordSeria(dto.getPasswordSeria());
//        entity.setCreatedDate(LocalDateTime.now());
//        entity.setVisible(true);
//        entity.setStatus(GeneralStatus.ACTIVE);
//        transactionRepository.save(entity);
//    }
//
//
//    public CustomUserDetails getProfile() {
//
//        CustomUserDetails user = CurrentUser.getCurrentUser();
//        return user;
//    }
//

    public List<TransactionDTO> getTransaction(String clientId, Integer size, Integer page) {

        List<TransactionsEntity> search = transactionRepository.findByCardId(clientId, size, page * size);

        List<TransactionDTO> dtos = new ArrayList<>();
        for (TransactionsEntity transactionsEntity : search) {
            TransactionDTO dto = new TransactionDTO();
            dto.setId(transactionsEntity.getId());
            dto.setAmount(transactionsEntity.getAmount());
            dto.setCreatedDate(transactionsEntity.getCreatedDate());
            dto.setTransactionType(transactionsEntity.getTransactionType());
            dto.setStatus(transactionsEntity.getStatus());
            dto.setCardId(transactionsEntity.getCardId());
            dto.setTransferId(transactionsEntity.getTransferId());

            dtos.add(dto);
        }

        return dtos;

    }

    public List<TransactionDTO> getTransactionByCardNumber(Long number, Integer size, Integer page) {

        List<TransactionsEntity> search = transactionRepository.findByCardNumber(number, size, page * size);

        List<TransactionDTO> dtos = new ArrayList<>();
        for (TransactionsEntity transactionsEntity : search) {
            TransactionDTO dto = new TransactionDTO();
            dto.setId(transactionsEntity.getId());
            dto.setAmount(transactionsEntity.getAmount());
            dto.setCreatedDate(transactionsEntity.getCreatedDate());
            dto.setTransactionType(transactionsEntity.getTransactionType());
            dto.setStatus(transactionsEntity.getStatus());
            dto.setCardId(transactionsEntity.getCardId());
            dto.setTransferId(transactionsEntity.getTransferId());

            dtos.add(dto);
        }

        return dtos;

    }

    public List<TransactionDTO> getTransactionByProfileId(String clientId, Integer size, Integer page) {

        List<TransactionsEntity> search = transactionRepository.findByClientId(clientId, size, page * size);

        List<TransactionDTO> dtos = new ArrayList<>();
        for (TransactionsEntity transactionsEntity : search) {
            TransactionDTO dto = new TransactionDTO();
            dto.setId(transactionsEntity.getId());
            dto.setAmount(transactionsEntity.getAmount());
            dto.setCreatedDate(transactionsEntity.getCreatedDate());
            dto.setTransactionType(transactionsEntity.getTransactionType());
            dto.setStatus(transactionsEntity.getStatus());
            dto.setCardId(transactionsEntity.getCardId());
            dto.setTransferId(transactionsEntity.getTransferId());

            dtos.add(dto);
        }

        return dtos;

    }

    public List<TransactionDTO> getTransactionByPhone(String phone, Integer size, Integer page) {

        List<TransactionsEntity> search = transactionRepository.findByPhone12(phone, size, page * size);

        List<TransactionDTO> dtos = new ArrayList<>();
        for (TransactionsEntity transactionsEntity : search) {
            TransactionDTO dto = new TransactionDTO();
            dto.setId(transactionsEntity.getId());
            dto.setAmount(transactionsEntity.getAmount());
            dto.setCreatedDate(transactionsEntity.getCreatedDate());
            dto.setTransactionType(transactionsEntity.getTransactionType());
            dto.setStatus(transactionsEntity.getStatus());
            dto.setCardId(transactionsEntity.getCardId());
            dto.setTransferId(transactionsEntity.getTransferId());

            dtos.add(dto);
        }

        return dtos;

    }

    public List<TransactionDTO> getCreditCardId(String clientId, Integer size, Integer page) {

        List<TransactionsEntity> search = transactionRepository.findCreditByCardId(clientId, size, page * size);

        List<TransactionDTO> dtos = new ArrayList<>();
        for (TransactionsEntity transactionsEntity : search) {
            TransactionDTO dto = new TransactionDTO();
            dto.setTransactionType(transactionsEntity.getTransactionType());

            dtos.add(dto);
        }

        return dtos;

    }

    public List<TransactionDTO> getDebitCardId(String clientId, Integer size, Integer page) {

        List<TransactionsEntity> search = transactionRepository.findDebitByCardId(clientId, size, page * size);

        List<TransactionDTO> dtos = new ArrayList<>();
        for (TransactionsEntity transactionsEntity : search) {
            TransactionDTO dto = new TransactionDTO();
            dto.setTransactionType(transactionsEntity.getTransactionType());

            dtos.add(dto);
        }

        return dtos;

    }

    public List<TransactionDTO> getDebitCreditByMonthly(String month, Integer size, Integer page) {

        List<TransactionsEntity> search = transactionRepository.getDebitCreditByMonthly(Date.valueOf(month), size, page * size);

        List<TransactionDTO> dtos = new ArrayList<>();
        for (TransactionsEntity transactionsEntity : search) {
            TransactionDTO dto = new TransactionDTO();
            dto.setTransactionType(transactionsEntity.getTransactionType());

            dtos.add(dto);
        }

        return dtos;

    }
//
//
//    public List<TransactionDTO> filter(TransactionDTO dto) {
//        customTransactionRepository.filter(dto);
//        return null;
//    }
}
