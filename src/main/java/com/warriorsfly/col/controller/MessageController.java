package com.warriorsfly.col.controller;



import com.warriorsfly.col.configuration.web.WebsocketConfig;
import com.warriorsfly.col.domain.messages.MessageForm;
import com.warriorsfly.col.repository.message.MessageEntity;
import com.warriorsfly.col.repository.message.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/message")
@MessageMapping("/message")
public class MessageController {
    @Autowired
    private MessageRepository repository;

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping(value = "/create")
    private Mono<MessageEntity> sendToUser(@RequestBody MessageForm form) {
        var message = MessageEntity.builder()
                .message_to(form.getMessageTo())
                .body(form.getBody().toString())
                .sender(form.getSender())
                .receiver(form.getReceiver())
                .created_time(form.getCreatedTime())
                .build();
        switch (form.getMessageTo()) {

            case Room:
                template.convertAndSend(String.format("/queue/%s/%s","", form.getReceiver()), form.getBody());
                break;
            default:
                template.convertAndSendToUser(form.getReceiver(), "/user/queue/", form.getBody());
                break;
        }
        return Mono.just(message);
    //     var message = MessageEntity.builder()
    //     .message_to(form.getMessageTo())
    //             .body(form.getBody().toString())
    //             .sender(form.getSender())
    //             .receiver(form.getReceiver())
    //             .created_time(form.getCreatedTime())
    //             .build();
    //    return repository.save(message).flatMap(item->{
    //         switch (form.getMessageTo()) {

    //             case Room:
    //                 template.convertAndSend(BROADCASTS, form.getBody());
    //                 break;
    //             default:
    //                 template.convertAndSendToUser(form.getReceiver(), NOTIFICATIONS, form.getBody());
    //                 break;
    //         }
    //         return Mono.just(item);
    //     });
    }
}