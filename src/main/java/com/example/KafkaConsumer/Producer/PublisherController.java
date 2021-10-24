package com.example.KafkaConsumer.Producer;

import com.example.KafkaConsumer.Dto.User;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PublisherController {

    @Autowired
    KafkaTemplate<String, Object> template;

    @Autowired
    @Qualifier("topic1")
    NewTopic topic1;


    @PostMapping("/publishUser")
    public void publishUser(@RequestBody User user) {
        ListenableFuture<SendResult<String, Object>> future = template.send(topic1.name(), user);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failed to publish User");
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("User Published at "+ result.getRecordMetadata().timestamp());
            }
        });
    }

    @PostMapping("/publishString/{message}")
    public void publishString(@PathVariable("message") String message) {
        ListenableFuture<SendResult<String, Object>> future = template.send(topic1.name(), message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failed to publish String");
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("String Message Published at "+ result.getRecordMetadata().timestamp());
            }
        });
    }
}
