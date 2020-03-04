package com.thejaxon.daskafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer {
    public static final Logger logger = LoggerFactory.getLogger(Consumer.class.getName());
    Consumer(){    
        String groupID = "dasKafkaApp";        
        
        //1- Consumer config
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, FXMLController.bootstrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); //earliest means read from the very beginning of the topic
        
        //2- Create consumer
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        
        //3- Subscribe the consumer to the producer's topic
        consumer.subscribe(Collections.singleton(FXMLController.topic)); //only subsrcibe to 1 topic using singleton
        
        new Thread()
        {
            @Override
            public void run() {
                //4- get the data from the producer
                while(true){
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for(ConsumerRecord<String, String> record: records){
                        FXMLController.consumerDetails.add(0, "" + record.value()); 
                        FXMLController.consumerDetails.add(1, "" + record.partition()); 
                        FXMLController.consumerDetails.add(2, "" + record.offset()); 

                        logger.info("key: " + record.key() + ", value: " + record.value());         
                    }
                }         
            }
        }.start();
    }
}
