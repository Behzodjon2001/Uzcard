package com.company.repository.custome;

import com.company.dto.CardDTO;
import com.company.dto.ClientDTO;
import com.company.entity.CardEntity;
import com.company.entity.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomeCardRepository {
    @Autowired
    private EntityManager entityManager;

    public List<CardEntity> filter(CardDTO dto) {

        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT a FROM CardEntity a ");
        builder.append(" where status ='ACTIVE' ");

        if (dto.getId() != null) {
            builder.append(" and a.id = '" + dto.getId() + "' ");
        }

        if (dto.getNumber() != null) {
            builder.append(" and a.name = '" + dto.getNumber() + "' ");
        }
        // Select a from ProfileEntity a where title = 'asdasd'; delete from sms-- fdsdfsdfs'
        if (dto.getStatus() != null) {
            builder.append(" and a.status = '" + dto.getStatus() + "' ");
        }

        if (dto.getPhone() != null) {
            builder.append(" and a.phone  ='" + dto.getPhone() + "'");
        }

        if (dto.getBalance() != null) {
            builder.append(" and a.balance='" + dto.getBalance() + "' ");
        }

        if (dto.getClientId() != null) {
            builder.append(" and a.client_id='" + dto.getClientId() + "' ");
        }

        if (dto.getCompanyId() != null) {
            builder.append(" and a.company_id='" + dto.getCompanyId() + "' ");
        }

        Query query = entityManager.createQuery(builder.toString());
        List<CardEntity> cardList = query.getResultList();

        return cardList;
    }
}
