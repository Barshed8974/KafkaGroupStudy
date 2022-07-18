package com.example.kafkagroupstudy.db_classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Transactional
public class CardDetails {
    @Id
    private String cardNumber;
    private String cardType;
    private String billingCycle;
    private String issuingBank;
    private double creditLimit;
    private String cardHolderName;
    private String expiryDate;
    private String uuId;
}
