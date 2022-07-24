package com.company.repository.custome;

import com.company.dto.ClientDTO;
import com.company.entity.ClientEntity;
import com.company.entity.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomeClientRepository {
    @Autowired
    private EntityManager entityManager;

    public List<ClientEntity> filter(ClientDTO dto) {

        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT a FROM ClientEntity a ");
        builder.append(" where visible = true ");

        if (dto.getId() != null) {
            builder.append(" and a.id = '" + dto.getId() + "' ");
        }

        if (dto.getName() != null) {
            builder.append(" and a.name = '" + dto.getName() + "' ");
        }
        // Select a from ProfileEntity a where title = 'asdasd'; delete from sms-- fdsdfsdfs'
        if (dto.getStatus() != null) {
            builder.append(" and a.status = '" + dto.getStatus() + "' ");
        }

        if (dto.getSurname() != null) {
            builder.append(" and a.surname  ='" + dto.getSurname() + "'");
        }

        if (dto.getMiddleName() != null) {
            builder.append(" and a.middle name='" + dto.getMiddleName() + "' ");
        }

        if (dto.getPasswordSeria() != null) {
            builder.append(" and a.password Number='" + dto.getPasswordSeria() + "' ");
        }

        if (dto.getPasswordNumber() != null) {
            builder.append(" and a.passwordSeria='" + dto.getPasswordNumber() + "' ");
        }


        Query query = entityManager.createQuery(builder.toString());
        List<ClientEntity> clieList = query.getResultList();

        return clieList;
    }
}
