package com.example.kafkagroupstudy.repository;

import com.example.kafkagroupstudy.db_classes.CardDetails;
import com.example.kafkagroupstudy.db_classes.ConsumerModel;
import com.example.kafkagroupstudy.db_classes.TransactionDetails;
import com.example.kafkagroupstudy.kafkaclasses.DataConsumer;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DataBase {

    private static final Logger LOG = Logger.getLogger(DataBase.class);
    Layout layout=new PatternLayout("%d %p %C %M %m %n");
    Appender appender=new ConsoleAppender(layout);
    @Autowired
    private DataConsumer dataConsumer;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardDetailsRepository cardDetailsRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    public List<ConsumerModel> addDataToDatabase()
    {
        LOG.addAppender(appender);
        LOG.debug("database function started");
        List<ConsumerModel> modelArrayList = dataConsumer.consumeData();
        if (!modelArrayList.isEmpty()) {
            LOG.info("adding data to database.....!!");
            for (ConsumerModel cardModel: modelArrayList
            ) {
                if (cardModel!=null) {
                    TransactionDetails transactionDetails=setTransactionDetails(cardModel);
                    CardDetails cardDetails=setCardDetails(cardModel);
                    LOG.info("saving data to transactionDetails.....!!");
                    transactionRepository.save(transactionDetails);
                    LOG.info("saving data to cardDetails.....!!");
                    cardDetailsRepository.save(cardDetails);
                }
            }
        }
        else
        {
            LOG.debug("list is empty, nothing to add in database......!!!!");
        }

        return modelArrayList;
    }

    private CardDetails setCardDetails(ConsumerModel cardModel) {
        CardDetails cardDetails=new CardDetails();
        cardDetails.setCardNumber(cardModel.getCardNumber());
        cardDetails.setCardHolderName(cardModel.getCardHolderName());
        cardDetails.setExpiryDate(cardModel.getExpiryDate());
        cardDetails.setCreditLimit(cardModel.getCreditLimit());
        cardDetails.setCardType(cardModel.getCardType());
        cardDetails.setBillingCycle(cardModel.getBillingDate());
        cardDetails.setIssuingBank(cardModel.getIssuingBank());
        cardDetails.setUuId(cardModel.getUuId());
        return cardDetails;
    }

    private TransactionDetails setTransactionDetails(ConsumerModel consumerModel) {
        TransactionDetails transactionDetails=new TransactionDetails();
        transactionDetails.setUuId(consumerModel.getUuId());
        transactionDetails.setStoreName(consumerModel.getStoreName());
        transactionDetails.setRegion(consumerModel.getRegion());
        transactionDetails.setProductName(consumerModel.getProductName());
        transactionDetails.setProductPrice(consumerModel.getProductPrice());
        transactionDetails.setProductQuantity(consumerModel.getProductQuantity());
        transactionDetails.setCardNumber(consumerModel.getCardNumber());
        return transactionDetails;
    }
}
