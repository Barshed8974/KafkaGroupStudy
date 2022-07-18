package com.example.kafkagroupstudy.kafkaclasses;

import com.example.kafkagroupstudy.db_classes.ConsumerModel;
import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Properties;

@Component
public class ProducerDemo {
    private static final Logger logger= LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public void producerMethod() {
        logger.info("hi");

        ArrayList<ConsumerModel> myModels=new ArrayList<>();
        myModels.add(new ConsumerModel("5FDC4CFC-D1F8-06FD-A918-383CA94BC163","Reebok","Himachal Pradesh",
                "Jeans",15.5,10,"Discover","Discover","6480200000000000",
                "Brenda D Peterson","Jan-22","4",22700
        ));
        myModels.add(new ConsumerModel("F1A76237-3542-3693-24DE-56B842D7E741","Apple","Tripura",
                "Laptops",35.02,10,"Diners Club International","Diners Club","30295200000000",
                "Dawn U Reese","Dec-16","2",10080));
        myModels.add(new ConsumerModel("DCB8926A-1036-1473-D114-62B5A4792BFE","Nike","Dadra and Nagar Haveli",
                "Laptops",10,10,"visa","HDFC","30082500000000",
                "Sam","Mar 22","9",10098));
        myModels.add(new ConsumerModel("DCB8926A-1036-1473-D114-62B5A4792BYU","ADIDAS","Pondicherry",
                "Shoe",100,10,"Master Card","HDFC","30082500000056",
                "Ali","May 92","22",1000000));

        //create producer properties
        Properties properties=new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //create producer
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<>(properties);

        //create a producer record
        for (ConsumerModel myModel : myModels) {
            ProducerRecord<String, String> producerRecord = getData(myModel);

            //send data
            kafkaProducer.send(producerRecord);
        }

        //flush and close
        kafkaProducer.flush();
        kafkaProducer.close();
    }

    private  ProducerRecord<String,String> getData(ConsumerModel myModel)
    {
        return new ProducerRecord<>("demo_java3", new Gson().toJson(myModel));
    }
}
