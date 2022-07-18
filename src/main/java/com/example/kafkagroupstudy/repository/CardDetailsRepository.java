package com.example.kafkagroupstudy.repository;

import com.example.kafkagroupstudy.db_classes.CardDetails;
import com.example.kafkagroupstudy.db_classes.ConsumerModel;
import com.example.kafkagroupstudy.db_classes.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CardDetailsRepository extends JpaRepository<CardDetails,String> {
    @Query("SELECT u FROM CardDetails u WHERE u.cardNumber = :n" )
    public CardDetails findCardDetailsByCardNumber(@Param("n")@RequestParam("cardNumber") String cardNumber);
}
