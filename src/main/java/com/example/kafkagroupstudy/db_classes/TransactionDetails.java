package com.example.kafkagroupstudy.db_classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Transactional
@Table(name = "Transaction_Details")
public class TransactionDetails {
    @Id
    private String uuId;
    private String storeName;
    private String region;
    private double productPrice;
    private int productQuantity;
    private String productName;
    private String cardNumber;
}
