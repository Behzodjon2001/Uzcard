package com.company.repository;

import com.company.entity.TransferEntity;
import com.company.mapper.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransferRepository extends JpaRepository<TransferEntity,String> {

    @Query(value = "SELECT p.id as transfer_id, p.to_card_id as toCard, "+
            "p.amount as amount, p.from_card_id as fromCard, p.created_date as createdDate, p.status as status, "+
           " c.id as cardIdC, c.number as numberC, c.phone as phoneC,c.client_id as clientIdC, "+
            " cl.name as nameC, cl.surname as surnameC, "+
            " ca.id as cardIdD, ca.number as numberD, ca.phone as phoneD, ca.client_id as clientIdD, "+
            " cle.name as nameD, cle.surname as surnameD "+
            "from  transfer as p "+
            "inner JOIN card as ca on p.to_card_id = ca.id "+
            "inner JOIN card as c on p.from_card_id = c.id "+
           " inner JOIN client as cl on cl.id = c.client_id "+
            "inner JOIN client as cle on cle.id = ca.client_id "+
            "where p.id =:TransferId", nativeQuery = true)
    List<TransactionInfo> getTransfer(@Param("TransferId") String TransferId);
}
