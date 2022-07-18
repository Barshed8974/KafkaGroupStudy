package com.example.kafkagroupstudy;


import com.example.kafkagroupstudy.kafkaclasses.ProducerDemo;
import com.example.kafkagroupstudy.repository.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaGroupStudyApplication implements CommandLineRunner {

    @Autowired
    DataBase dataBase;
    @Autowired
    ProducerDemo producerDemo;

    public static void main(String[] args) {
        SpringApplication.run(KafkaGroupStudyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        producerDemo.producerMethod();
        dataBase.addDataToDatabase();
    }
}
