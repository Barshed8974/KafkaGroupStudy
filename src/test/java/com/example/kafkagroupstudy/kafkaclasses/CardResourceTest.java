package com.example.kafkagroupstudy.kafkaclasses;

import com.example.kafkagroupstudy.db_classes.CardDetails;
import com.example.kafkagroupstudy.db_classes.TransactionDetails;
import com.example.kafkagroupstudy.repository.CardDetailsRepository;
import com.example.kafkagroupstudy.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.spi.CalendarDataProvider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CardResourceTest {

    private MockMvc mockMvc;
    @Mock
    TransactionRepository transactionRepository=mock(TransactionRepository.class);
    @Mock
    CardDetailsRepository cardDetailsRepository=mock(CardDetailsRepository.class);

    @InjectMocks
    CardResource cardResource;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(CardResource.class).build();
    }
    @Test
    void findAllCards() {
        List<TransactionDetails> list=new ArrayList<>();
        list.add(new TransactionDetails("ABC123","Nike",
                "Tripura",10,10,"shoe",
                "12345")
        );
        when(transactionRepository.findAll()).thenReturn(list);
        assertEquals(1,cardResource.findAllCards().size());
    }

    @Test
    void findByRegion() {
        List<TransactionDetails> list=new ArrayList<>();
        list.add(new TransactionDetails("ABC123","Nike",
                "Tripura",10,10,"shoe",
                "12345")
        );
        when(transactionRepository.findByRegion("Tripura")).thenReturn(list);
        assertEquals("Tripura",cardResource.findByRegion("Tripura").get(0).getRegion());
    }

    @Test
    void findTransactionsById() {
        when(transactionRepository.findTransactionDetailsByTransactionId("ABC123")).thenReturn(
                new TransactionDetails("ABC123","Nike",
                        "Tripura",10,10,"shoe",
                        "12345")
        );
        assertEquals("ABC123",cardResource.findTransactionsById("ABC123").getUuId());
    }

    @Test
    void findTransactionByCardNumber() {
        List<TransactionDetails> list=new ArrayList<>();
        list.add(new TransactionDetails("ABC123","Nike",
                "Tripura",10,10,"shoe",
                "12345")
        );
        when(transactionRepository.findTransactionDetailsByCardNumber("12345")).thenReturn(list);
        assertEquals("Tripura",cardResource.findTransactionByCardNumber("12345").get(0).getRegion());

    }

    @Test
    void findCardDetailsByCardNumber() {
        CardDetails cardDetails=new CardDetails("12345",
                "Visa","23","HDBC",10000,
                "Ajay","dec 2028","ABC123");
        when(cardDetailsRepository.findCardDetailsByCardNumber("12345"))
                .thenReturn(cardDetails);
        assertEquals("12345",cardResource.findCardDetailsByCardNumber("12345").getCardNumber());
    }

    @Test
    void message() {
        assertEquals("hello",cardResource.message());

    }
}