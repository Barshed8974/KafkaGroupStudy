package com.example.kafkagroupstudy.repository;

import com.example.kafkagroupstudy.db_classes.ConsumerModel;
import com.example.kafkagroupstudy.db_classes.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionDetails,String> {
    @Query("SELECT u FROM TransactionDetails u WHERE u.region = :n" )
    public List<TransactionDetails> findByRegion(@Param("n")@RequestParam("region") String region);
    @Query("SELECT u FROM TransactionDetails u WHERE u.cardNumber = :n" )
    public List<TransactionDetails> findTransactionDetailsByCardNumber(@Param("n")@RequestParam("cardNumber") String cardNumber);
    @Query("SELECT u FROM TransactionDetails u WHERE u.uuId = :n" )
    public TransactionDetails findTransactionDetailsByTransactionId(@Param("n")@RequestParam("transactionId") String transactionId);
}