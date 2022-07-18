package com.example.kafkagroupstudy.db_classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Transactional
@Table(name = "card_model")
public class ConsumerModel {
    @Id
    private String uuId;
    private String storeName;
    private String region;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String cardType;
    private String issuingBank;
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String billingDate;
    private double creditLimit;
}

