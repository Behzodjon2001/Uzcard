package com.company.repository.custome;

import com.company.dto.ProfileDTO;
import com.company.dto.transfer.TransferDTO;
import com.company.entity.ProfileEntity;
import com.company.entity.TransferEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomeTransferRepository {
    @Autowired
    private EntityManager entityManager;

    public List<TransferEntity> filter(TransferDTO dto) {

        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT a FROM TransferEntity a ");
        builder.append(" where status = 'SUCCESS' ");


        if (dto.getFromCardId() != null) {
            builder.append(" and a.fromCardId = '" + dto.getFromCardId() + "' ");
        }
        // Select a from ProfileEntity a where title = 'asdasd'; delete from sms-- fdsdfsdfs'
        if (dto.getToCardId() != null) {
            builder.append(" and a.toCardId = '" + dto.getToCardId() + "' ");
        }

        if (dto.getAmount() != null) {
            builder.append(" and a.amount  = " + dto.getAmount() + "");
        }

        if (dto.getTotalAmount() != null) {
            builder.append(" and a.totalAmount = " + dto.getTotalAmount() + " ");
        }

        Query query = entityManager.createQuery(builder.toString());
        List<TransferEntity> profileList = query.getResultList();

        return profileList;
    }

}
