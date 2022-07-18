package com.example.kafkagroupstudy.kafkaclasses;


import com.example.kafkagroupstudy.db_classes.ConsumerModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
@PropertySource("classpath:app.properties")
public class DataConsumer {

    private static final Logger LOG = Logger.getLogger(DataConsumer.class);
    Layout layout=new PatternLayout("%d %p %C %M %m %n");
    Appender appender=new ConsoleAppender(layout);

    @Value("${kafka.BOOSTRAP_SERVERS}")
    private  String boostrapServer;
    @Value(("${kafka.GroupId}"))
    private  String groupId;
    @Value("${kafka.topic}")
    private  String topic;

    private final ArrayList<ConsumerModel> consumerModelArrayList =new ArrayList<>();
    public List<ConsumerModel> consumeData() {
        LOG.addAppender(appender);
        LOG.debug("Starting Kafka Consumer........!!!!");

        // create consumer configs
        LOG.debug("Defining consumer properties......!!!");
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        LOG.debug("Consumer properties set......!!!");

        // create consumer

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties); consumer) {
            // subscribe consumer to our topic(s)

            LOG.info("consumer subscribing to kafka topic = "+topic);
            consumer.subscribe(List.of(topic));

            // poll for new data
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
                LOG.info("Polling data.......!!!"+records.isEmpty());


                for (ConsumerRecord<String, String> recordFromRecords : records) {
                    LOG.info("reading data........!!!!");
                    ObjectMapper mapper = new ObjectMapper();
                    LOG.info("converting data from json to object........!!");
                    ConsumerModel myModel = mapper.readValue(recordFromRecords.value(), ConsumerModel.class);
                    LOG.debug(myModel.getCardNumber());
                     consumerModelArrayList.add(myModel);

                }
                consumer.wakeup();
            }
        } catch (WakeupException e) {
            LOG.info("Wake up exception!");
            if (!consumerModelArrayList.isEmpty())
            {
                LOG.debug("data successfully fetched......!!");
            }
            else
            {
                LOG.debug("failed to fetch the data......!!!");
            }
        } catch (Exception e) {
            LOG.error("Unexpected exception");//need to change
        } finally {
            // this will also commit the offsets if need be
            LOG.info("The consumer is now gracefully closed");
        }
        return consumerModelArrayList;
    }

}