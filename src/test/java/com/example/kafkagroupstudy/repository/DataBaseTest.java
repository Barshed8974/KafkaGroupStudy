package com.example.kafkagroupstudy.repository;

import com.example.kafkagroupstudy.db_classes.ConsumerModel;
import com.example.kafkagroupstudy.kafkaclasses.CardResource;
import com.example.kafkagroupstudy.kafkaclasses.DataConsumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


class DataBaseTest {

    @Autowired
    private CardRepository cardRepository=mock(CardRepository.class);
    @Autowired
    private CardDetailsRepository cardDetailsRepository=mock(CardDetailsRepository.class);
    @Autowired
    private TransactionRepository transactionRepository=mock(TransactionRepository.class);
    private MockMvc mockMvc;
    @Autowired
    DataConsumer dataConsumer= mock(DataConsumer.class);
    @InjectMocks
    DataBase dataBase;
    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(CardResource.class).build();
    }
    @Test
    void addDataToDatabase() {
        List<ConsumerModel> list=new ArrayList<>();
        ConsumerModel consumerModel=new ConsumerModel("5FDC4CFC-D1F8-06FD-A918-383CA94BC163","Reebok","Himachal Pradesh",
                "Jeans",15.5,10,"Discover","Discover","6480200000000000",
                "Brenda D Peterson","Jan-22","4",22700
        );
        list.add(consumerModel);
        when(dataConsumer.consumeData()).thenReturn(list);
        List<ConsumerModel> outputList=dataBase.addDataToDatabase();
        assertEquals(1,outputList.size());
    }
}