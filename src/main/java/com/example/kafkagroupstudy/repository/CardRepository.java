package com.example.kafkagroupstudy.repository;

import com.example.kafkagroupstudy.db_classes.ConsumerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CardRepository extends JpaRepository<ConsumerModel,Long> {
    @Query("SELECT u FROM ConsumerModel u WHERE u.cardNumber = :n" )
    public List<ConsumerModel> findByCardnumber(@Param("n")@RequestParam("cardnumber") String cardnumber);
    @Query("SELECT u FROM ConsumerModel u WHERE u.region = :n" )
    public List<ConsumerModel> findByRegion(@Param("n")@RequestParam("region") String region);
    @Query("SELECT u FROM ConsumerModel u WHERE u.uuId = :n" )
    public List<ConsumerModel> findById(@Param("n")@RequestParam("id") int id);
}