package com.warriorsfly.induction.controller;


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

@RestController(value = "/message")
public class MessageProducerController {

    private String key;

    @Autowired
    private ReactiveRedisTemplate<String, String> template;

    @Autowired
    private MessageRepository repository;

    @PostMapping(value = "/create")
    private Mono<MessageEntity> createMessage(MessageForm form){
        var record = StreamRecords.newRecord()
                .ofObject(form)
                .withStreamKey(key);

        return template
                .opsForStream()
                .add(record)
                .flatMap(recordId -> {
                    var message = new MessageEntity(recordId.getValue(),form.getBody(),form.getSender().getId(),form.getMessageTo(),form.getCreatedTime());
                    return repository.save(message);
                });
    }
}