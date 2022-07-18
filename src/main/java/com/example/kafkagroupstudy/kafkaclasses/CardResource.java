package com.example.kafkagroupstudy.kafkaclasses;


import com.example.kafkagroupstudy.db_classes.CardDetails;
import com.example.kafkagroupstudy.db_classes.TransactionDetails;
import com.example.kafkagroupstudy.repository.CardDetailsRepository;
import com.example.kafkagroupstudy.repository.TransactionRepository;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Component
@RequestMapping("/cards")
public class CardResource {
    private static final Logger LOG = Logger.getLogger(CardResource.class);
    Layout layout=new PatternLayout("%d %p %C %M %m %n");
    Appender appender=new ConsoleAppender(layout);
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    @GetMapping("/transaction-details")
    public List<TransactionDetails> findAllCards()
    {
        LOG.addAppender(appender);
        LOG.info("fetching data from database....!!!!");
        List<TransactionDetails> transactionDetails=new ArrayList<>();
        try {
            if (!transactionRepository.findAll().isEmpty())
            {
                LOG.debug("transaction details data from database....!!!!");
                transactionDetails.addAll(transactionRepository.findAll());
            }
            else
            {
                LOG.debug("transaction details not found....!!!!");
            }
        }
        catch (Exception exception)
        {
            LOG.error(exception);
        }
        return transactionDetails;
    }

    @GetMapping("/find-by-region")
    public List<TransactionDetails> findByRegion(@RequestParam("region") String area)
    {
        LOG.addAppender(appender);
        LOG.info("fetching data by region from database....!!!!");
        List<TransactionDetails> transactionDetails=new ArrayList<>();
        try
        {
            if (!transactionRepository.findByRegion(area).isEmpty())
            {
                LOG.debug("data by region found from database....!!!!");
                transactionDetails.addAll(transactionRepository.findByRegion(area));
            }
            else
            {
                LOG.debug("region is empty....!!!!");
            }
        }
        catch (Exception exception)
        {
            LOG.error(exception);
        }
        return transactionDetails;
    }

    @GetMapping("/find-transaction-by-id")
    public TransactionDetails findTransactionsById(@RequestParam("id") String id)
    {
        TransactionDetails transactionDetails=new TransactionDetails();
        LOG.addAppender(appender);
        LOG.info("fetching transaction data by Transaction Id from database....!!!!");
        try
        {
            if (!transactionRepository.findTransactionDetailsByCardNumber(id).isEmpty())
            {
                LOG.debug("found transaction details with that id from database....!!!!");
                transactionDetails=transactionRepository.findTransactionDetailsByTransactionId(id);
            }
            else
            {
                LOG.debug("no data found....!!!!");
            }
            return transactionRepository.findTransactionDetailsByTransactionId(id);
        }
        catch (Exception exception)
        {
            LOG.error(exception);
        }
        return transactionDetails;
    }

    @GetMapping("/find-transaction-details-by-card-number")
    public List<TransactionDetails> findTransactionByCardNumber(@RequestParam("card-number") String cardNumber)
    {
        LOG.addAppender(appender);
        List<TransactionDetails> transactionDetails=new ArrayList<>();
        LOG.info("fetching transaction data by Card Number from database....!!!!");
        try {
            if (!transactionRepository.findTransactionDetailsByCardNumber(cardNumber).isEmpty())
            {
                LOG.debug("found data from database by card number....!!!!");
                transactionDetails.addAll(transactionRepository.findTransactionDetailsByCardNumber(cardNumber));
            }
            else
            {
                LOG.debug("no transaction found found....!!!!");
            }
            return transactionRepository.findTransactionDetailsByCardNumber(cardNumber);
        }
        catch (Exception e)
        {
            LOG.error(e);
        }
        return transactionDetails;
    }
    @GetMapping("/find-card-details-by-card-number")

    public CardDetails findCardDetailsByCardNumber(@RequestParam("card-number") String cardNumber)
    {
        LOG.addAppender(appender);
        LOG.info("fetching transaction data by Card Number from database....!!!!");
        CardDetails cardDetails=new CardDetails();
        try {
            if (cardDetailsRepository.findCardDetailsByCardNumber(cardNumber)!=null)
            {
                LOG.debug("found data from database....!!!!");
                cardDetails=cardDetailsRepository.findCardDetailsByCardNumber(cardNumber);
            }
            else
            {
                LOG.debug("data not found....!!!!");
            }
        }
        catch (Exception exception)
        {
            LOG.error(exception);
        }
        return cardDetails;
    }
    @GetMapping("/message")
    public String message()
    {
        return "hello";
    }
}