package com.warriorsfly.induction.controller;


import com.warriorsfly.induction.configuration.WebsocketConfig;
import com.warriorsfly.induction.domain.messages.MessageForm;
import com.warriorsfly.induction.repository.message.MessageEntity;
import com.warriorsfly.induction.repository.message.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.warriorsfly.induction.configuration.WebsocketConfig.*;

@RestController(value = "/message")
public class MessageProducerController {

    @Autowired
    private ReactiveRedisTemplate<String, String> template;

    @Autowired
    private MessageRepository repository;

    @PostMapping(value = "/create")
    private Mono<MessageEntity> createMessage(MessageForm form){
        String key;
        switch (form.getMessageTo()){
            case GROUP:
                key = GROUPS;
                break;
            case Room:
                key = BROADCASTS;
                break;
            default:
                key = NOTIFICATIONS;
                break;
        }
        var record = StreamRecords.newRecord()
                .ofObject(form.getBody())
                .withStreamKey(String.format("%s:%s",key,form.getReceiver()));

        return template
                .opsForStream()
                .add(record)
                .flatMap(recordId -> {
                    var message = new MessageEntity(recordId.getValue(),form.getBody(),form.getSender(),form.getMessageTo(),form.getCreatedTime());
                    return repository.save(message);
                });
    }
}