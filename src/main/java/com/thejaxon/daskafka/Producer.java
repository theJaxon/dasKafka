package com.thejaxon.daskafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer {
    Producer(){
         final Logger logger = LoggerFactory.getLogger(Producer.class);

        //1- Creating producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, FXMLController.bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        
        //2- Create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        
        //3- Create producer record
        ProducerRecord<String, String> record = new ProducerRecord<>(FXMLController.topic, FXMLController.value); //Topic: Value
        
        //4- Send data
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e == null){ //means record was successfully sent
                    logger.info("Received Metadata:\n" +
                                "Topic:" + recordMetadata.topic() + "\n" +
                                "Partition:" + recordMetadata.partition() + "\n" + 
                                "Offset: " + recordMetadata.offset() + "\n" + 
                                "TimeStamp" + recordMetadata.timestamp());
                }
                else{
                    logger.error("Error while producing ", e);
                }
                
            }
        });
        
        producer.flush(); //force the data to be sent to the consumer [This is because of the async]
        producer.close();
        
        
    }
}
