package com.company.service;

import com.company.config.CustomUserDetails;
import com.company.dto.CardDTO;
import com.company.dto.ClientDTO;
import com.company.entity.CardEntity;
import com.company.entity.ClientEntity;
import com.company.entity.CompanyEntity;
import com.company.enums.GeneralStatus;
import com.company.exception.BadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.exception.NotAllowedException;
import com.company.repository.CardRepository;
import com.company.repository.ClientRepository;
import com.company.repository.CompanyRepository;
import com.company.repository.custome.CustomeCardRepository;
import com.company.util.CardNumberGenerator;
import com.company.util.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardNumberGenerator cardNumberGenerator;
    @Autowired
    private CustomeCardRepository customCardRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ProfileService profileService;



    public CardDTO create(CardDTO dto) {
        Optional<CardEntity> optional = cardRepository.findByNumber(dto.getNumber());
        if (optional.isPresent()) {
            log.error("User already exists {}", dto);
            throw new BadRequestException("User already exists");
        }

        CardEntity entity = new CardEntity();
        entity.setNumber(Long.valueOf("8600" + cardNumberGenerator.generateRandom(12)));
        entity.setHiddenNumber(cardNumberGenerator.maskCCNumber(String.valueOf(entity.getNumber())));
        entity.setBalance(dto.getBalance());
        entity.setPhone(dto.getPhone());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setStatus(GeneralStatus.ACTIVE);
        entity.setExpiredDate(dto.getExpiredDate());

        Optional<ClientEntity> client = clientRepository.findById(dto.getClientId());
        entity.setClientId(client.get().getId());

        Optional<CompanyEntity> company = companyRepository.findById(dto.getCompanyId());
        entity.setCompanyId(company.get().getId());

        cardRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setExpiredDate(entity.getExpiredDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }


    public void phoneUpdate(String pId, CardDTO dto) {

        Optional<CardEntity> profile = cardRepository.findById(pId);

        if (profile.isEmpty()) {
            log.error("Card Not Found {}", dto);
            throw new ItemNotFoundException("Card Not Found ");
        }

        CardEntity entity = profile.get();
        entity.setPhone(dto.getPhone());
        cardRepository.save(entity);
    }

    public void statusUpdate(String pId, CardDTO dto) {

        Optional<CardEntity> profile = cardRepository.findById(pId);

        if (profile.isEmpty()) {
            log.error("Card Not Found {}", dto);
            throw new ItemNotFoundException("Card Not Found ");
        }

        CardEntity entity = profile.get();
        if (profileService.getProfile().getRole().name().equals("ROLE_BANK")){
            entity.setStatus(dto.getStatus());
        } else {
            if (dto.getStatus().name().equals("BLOCK")){
                entity.setStatus(dto.getStatus());
            } else {
                throw new NotAllowedException("Not Allowed");
            }
        }
        cardRepository.save(entity);
    }


    public CustomUserDetails getProfile() {

        CustomUserDetails user = CurrentUser.getCurrentUser();
        return user;
    }

    public CardEntity get(String key) {
        return cardRepository.findById(key).orElseThrow(() -> {
            log.error("This category not found {}" , key);
            throw new ItemNotFoundException("This category not found");
        });
    }


    public CardDTO getCardById(String cardId) {
        Optional<CardEntity> cardIdEntity = cardRepository.findById(cardId);
        CardEntity cardEntity = cardIdEntity.get();
        CardDTO dto = new CardDTO();
        dto.setId(cardEntity.getId());
        dto.setNumber(cardEntity.getNumber());
        dto.setHiddenNumber(cardEntity.getHiddenNumber());
        dto.setExpiredDate(cardEntity.getExpiredDate());
        dto.setPhone(cardEntity.getPhone());
        dto.setStatus(cardEntity.getStatus());
        dto.setBalance(cardEntity.getBalance());
        dto.setCompanyId(cardEntity.getCompanyId());
        dto.setClientId(cardEntity.getClientId());
        dto.setCreatedDate(cardEntity.getCreatedDate());
        dto.setId(cardEntity.getId());

        return dto;
    }

    public List<CardDTO> getCardByPhone(String cardId) {
        List<CardEntity> cardIdEntity = cardRepository.findAllByPhone(cardId);
        List<CardDTO> entityList = new ArrayList<>();
        for (CardEntity entity: cardIdEntity) {

            CardDTO dto = new CardDTO();
            dto.setId(entity.getId());
            dto.setNumber(entity.getNumber());
            dto.setHiddenNumber(entity.getHiddenNumber());
            dto.setExpiredDate(entity.getExpiredDate());
            dto.setPhone(entity.getPhone());
            dto.setStatus(entity.getStatus());
            dto.setBalance(entity.getBalance());
            dto.setCompanyId(entity.getCompanyId());
            dto.setClientId(entity.getClientId());
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());

            entityList.add(dto);
        }
        return entityList;
    }

    public List<CardDTO> getCardByClientId(String cardId) {
        List<CardEntity> cardIdEntity = cardRepository.findAllByClientId(cardId);
        List<CardDTO> entityList = new ArrayList<>();
        for (CardEntity entity: cardIdEntity) {

            CardDTO dto = new CardDTO();
            dto.setId(entity.getId());
            dto.setNumber(entity.getNumber());
            dto.setHiddenNumber(entity.getHiddenNumber());
            dto.setExpiredDate(entity.getExpiredDate());
            dto.setPhone(entity.getPhone());
            dto.setStatus(entity.getStatus());
            dto.setBalance(entity.getBalance());
            dto.setCompanyId(entity.getCompanyId());
            dto.setClientId(entity.getClientId());
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());

            entityList.add(dto);
        }
        return entityList;
    }

    public CardDTO getCardByNumber(Long cardId) {
        Optional<CardEntity> cardIdEntity = cardRepository.findByNumber(cardId);
        CardEntity cardEntity = cardIdEntity.get();
        CardDTO dto = new CardDTO();
        dto.setId(cardEntity.getId());
        dto.setNumber(cardEntity.getNumber());
        dto.setHiddenNumber(cardEntity.getHiddenNumber());
        dto.setExpiredDate(cardEntity.getExpiredDate());
        dto.setPhone(cardEntity.getPhone());
        dto.setStatus(cardEntity.getStatus());
        dto.setBalance(cardEntity.getBalance());
        dto.setCompanyId(cardEntity.getCompanyId());
        dto.setClientId(cardEntity.getClientId());
        dto.setCreatedDate(cardEntity.getCreatedDate());
        dto.setId(cardEntity.getId());

        return dto;
    }

    public String getCardByBalanceNumber(Long cardId) {
        Optional<CardEntity> cardIdEntity = cardRepository.findByNumber(cardId);
        CardEntity cardEntity = cardIdEntity.get();

        return cardEntity.getBalance() + " so'm";
    }

    public List<CardEntity> filter(CardDTO dto) {
        return customCardRepository.filter(dto);

    }
}
