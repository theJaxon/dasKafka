package com.thejaxon.daskafka;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FXMLController implements Initializable {
    static String bootstrapServer = "127.0.0.1:9092";
    static String topic = "";
    static String value = "";
    static ArrayList<String> consumerDetails = new ArrayList<String>();

    @FXML public JFXTextField topicNameTF , topicValueTF;
    @FXML private JFXListView<String> recordLV, keyValuePairLV;
    
    @FXML private void producerSubmitClicked(ActionEvent event){
        topic =  topicNameTF.getText();
        value = topicValueTF.getText();
        
        Producer p = new Producer();
    }
    
    @FXML private void consumerSubmitClicked(ActionEvent event){
        Consumer c = new Consumer();
        recordLV.getItems().add((consumerDetails.get(0) + " \t " + consumerDetails.get(1) + " \t " + consumerDetails.get(2)));                    
        //System.out.println("No of letters is: " + consumerDetails.get(0).length());
        
        String[] split = consumerDetails.get(0).split( " " );
        
        Map<String,Integer> splitMap = new HashMap<>();
        for(int i = 0; i < split.length; i++){
            //if the word is already found in the HashMap
            if(splitMap.containsKey(split[i])){
                //Increment the integer value of the word
                splitMap.put(split[i], splitMap.get(split[i])+1 );  
            }
            else{
                //Add the word to the HashMap with initial key value of 1
                splitMap.put(split[i], 1);
            }
        }
         keyValuePairLV.getItems().add(splitMap.keySet().toString());
         keyValuePairLV.getItems().add(splitMap.values().toString());
        
        
        System.out.println("Your words: \n");
        for(int i = 0; i < split.length; i++){
            System.out.println(split[i]);
        }
        
        for(int i = 0; i < split.length; i++){
            for (int B = 0; B < split.length; B++) {
                if (i != B && split[i].equals(split[B])) {
                    System.out.println("Repeated word:" + split[i]);
                }
            
            }
            
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
}
