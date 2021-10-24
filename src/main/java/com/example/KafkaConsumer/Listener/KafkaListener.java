package com.example.KafkaConsumer.Listener;

import com.example.KafkaConsumer.Dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(topics = "Topic1", groupId = "user_group", containerFactory = "userConcurrentKafkaListenerContainerFactory")
    public void consumeUser(User user) {
//        ObjectMapper mapper = new ObjectMapper();
//        Map map = mapper.readValue(message, Map.class);
        System.out.println("User consumed==="+user);
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "WebDomain", groupId = "message_group", containerFactory = "messageConcurrentKafkaListenerContainerFactory")
    public void consumeString(String str) {
//        ObjectMapper mapper = new ObjectMapper();
//        Map map = mapper.readValue(message, Map.class);
        System.out.println("String consumed==="+str);
    }
}
