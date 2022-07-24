package com.company.service;

import com.company.config.CustomUserDetails;
import com.company.dto.ClientDTO;
import com.company.entity.ClientEntity;
import com.company.enums.GeneralStatus;
import com.company.exception.BadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.ClientRepository;
import com.company.repository.custome.CustomeClientRepository;
import com.company.util.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CustomeClientRepository customClientRepository;


    public ClientDTO create(ClientDTO dto) {
        Optional<ClientEntity> optional = clientRepository.
                findByPasswordNumberAndPasswordSeria(dto.getPasswordNumber(), dto.getPasswordSeria());
        if (optional.isPresent()) {
            log.error("User already exists {}", dto);
            throw new BadRequestException("User already exists");
        }

        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddleName(dto.getMiddleName());
        entity.setPhone(dto.getPhone());
        entity.setPasswordNumber(dto.getPasswordNumber());
        entity.setPasswordSeria(dto.getPasswordSeria());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);

        clientRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        dto.setStatus(entity.getStatus());
        return dto;
    }


    public void update(String passwordNumber, String passwordSeria, ClientDTO dto) {

        Optional<ClientEntity> profile = clientRepository.
                findByPasswordNumberAndPasswordSeria(passwordNumber,passwordSeria);

        if (profile.isEmpty()) {
            log.error("Client Not Found {}", dto);
            throw new ItemNotFoundException("Client Not Found ");
        }


        ClientEntity entity = profile.get();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddleName(dto.getMiddleName());
        entity.setPhone(dto.getPhone());
        entity.setPasswordNumber(dto.getPasswordNumber());
        entity.setPasswordSeria(dto.getPasswordSeria());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);
        clientRepository.save(entity);
    }


    public CustomUserDetails getProfile() {

        CustomUserDetails user = CurrentUser.getCurrentUser();
        return user;
    }

    public ClientDTO getClient(String clientId) {
        Optional<ClientEntity> clientIdEntity = clientRepository.findById(clientId);
        ClientEntity clientEntity = clientIdEntity.get();
        ClientDTO dto = new ClientDTO();
        dto.setId(clientEntity.getId());
        dto.setName(clientEntity.getName());
        dto.setSurname(clientEntity.getSurname());
        dto.setMiddleName(clientEntity.getMiddleName());
        dto.setPhone(clientEntity.getPhone());
        dto.setPasswordNumber(clientEntity.getPasswordNumber());
        dto.setPasswordSeria(clientEntity.getPasswordSeria());
        dto.setCreatedDate(clientEntity.getCreatedDate());

        return dto;
    }


    public List<ClientEntity> filter(ClientDTO dto) {
        return  customClientRepository.filter(dto);
    }

}
