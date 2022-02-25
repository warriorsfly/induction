package com.warriorsfly.col.controller;


import static com.warriorsfly.col.configuration.WebsocketConfig.BROADCASTS;
import static com.warriorsfly.col.configuration.WebsocketConfig.NOTIFICATIONS;

import com.warriorsfly.col.domain.messages.MessageForm;
import com.warriorsfly.col.repository.message.MessageEntity;
import com.warriorsfly.col.repository.message.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/message")
@MessageMapping("/message")
public class MessageController {
    @Autowired
    private MessageRepository repository;

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping(value = "/create")
    private Mono<MessageEntity> createMessage(MessageForm form) {

        var message = new MessageEntity(0L,  form.getSender(),form.getBody().toString(), form.getMessageTo(),form.getReceiver(), form.getCreatedTime());
        return repository.save(message).flatMap(item->{
            switch (form.getMessageTo()) {

                case Room:
                    template.convertAndSend(BROADCASTS, form.getBody());
                    break;
                default:
                    template.convertAndSendToUser(form.getReceiver(), NOTIFICATIONS, form.getBody());
                    break;
            }
            return Mono.just(item);
        });
    }
}