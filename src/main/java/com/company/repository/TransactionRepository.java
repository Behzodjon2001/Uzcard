package com.company.repository;

import com.company.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionsEntity, String> {

    @Query(value = "select v.id, v.amount, v.created_date, v.transaction_type," +
            " v.status, v.card_id, v.transfer_id " +
            "from transactions as v " +
            "where v.card_id =:clientId " +
            "limit :limit " +
            "offset :offset ", nativeQuery = true)
    List<TransactionsEntity> findByCardId(@Param("clientId") String clientId,
                                      @Param("limit") Integer limit,
                                      @Param("offset") Integer offset);

    @Query(value = "select v.id, v.amount, v.created_date, v.transaction_type," +
            " v.status, v.card_id, v.transfer_id " +
            "from transactions as v " +
            " inner JOIN card as c on c.id = v.card_id" +
            " where c.number =:cardNumber " +
            "limit :limit " +
            "offset :offset ", nativeQuery = true)
    List<TransactionsEntity> findByCardNumber(@Param("cardNumber") Long cardNumber,
                                      @Param("limit") Integer limit,
                                      @Param("offset") Integer offset);

    @Query(value = "select v.id, v.amount, v.created_date, v.transaction_type," +
            " v.status, v.card_id, v.transfer_id " +
            "from transactions as v " +
            " inner JOIN card as c on c.id = v.card_id" +
            " where c.client_id =:clientId " +
            "limit :limit " +
            "offset :offset ", nativeQuery = true)
    List<TransactionsEntity> findByClientId(@Param("clientId") String clientId,
                                      @Param("limit") Integer limit,
                                      @Param("offset") Integer offset);

    @Query(value = "select v.id, v.amount, v.created_date, v.transaction_type," +
            " v.status, v.card_id, v.transfer_id " +
            "from transactions as v " +
            " inner JOIN card as c on c.id = v.card_id " +
            " where c.phone =:phone1 " +
            " and c.status = 'ACTIVE' " +
            "limit :limit " +
            "offset :offset ", nativeQuery = true)
    List<TransactionsEntity> findByPhone12(@Param("phone1") String phone1,
                                      @Param("limit") Integer limit,
                                      @Param("offset") Integer offset);

    @Query(value = "select v.id, v.amount, v.created_date, v.transaction_type," +
            " v.status, v.card_id, v.transfer_id " +
            "from transactions as v " +
            "where v.card_id =:cardId" +
            " and v.transaction_type = 'CREDIT' " +
            "limit :limit " +
            "offset :offset ", nativeQuery = true)
    List<TransactionsEntity> findCreditByCardId(@Param("cardId") String cardId,
                                          @Param("limit") Integer limit,
                                          @Param("offset") Integer offset);

    @Query(value = "select v.id, v.amount, v.created_date, v.transaction_type," +
            " v.status, v.card_id, v.transfer_id " +
            "from transactions as v " +
            "where v.card_id =:cardId" +
            " and v.transaction_type = 'DEBIT' " +
            "limit :limit " +
            "offset :offset ", nativeQuery = true)
    List<TransactionsEntity> findDebitByCardId(@Param("cardId") String cardId,
                                          @Param("limit") Integer limit,
                                          @Param("offset") Integer offset);

    @Query(value = "select v.id, v.amount, v.created_date, v.transaction_type," +
            " v.status, v.card_id, v.transfer_id " +
            "from transactions as v " +
            "where v.created_date =:month" +
            " and (v.transaction_type = 'DEBIT'" +
            " or v.transaction_type = 'CREDIT') " +
            "limit :limit " +
            "offset :offset ", nativeQuery = true)
    List<TransactionsEntity> getDebitCreditByMonthly(@Param("month") Date month,
                                          @Param("limit") Integer limit,
                                          @Param("offset") Integer offset);

}
