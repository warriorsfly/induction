package com.warriorsfly.induction.controller;


import com.warriorsfly.induction.configuration.StompPrincipal;
import com.warriorsfly.induction.domain.messages.Message;
import com.warriorsfly.induction.domain.messages.MessageForm;
import com.warriorsfly.induction.repository.message.MessageEntity;
import com.warriorsfly.induction.repository.message.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

import static com.warriorsfly.induction.configuration.WebsocketConfig.*;

@Controller(value = "/message")
public class MessageController {
    @Autowired
    private MessageRepository repository;

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping(value = "/create")
    private Mono<MessageEntity> createMessage(MessageForm form) {

        var message = new MessageEntity(0,  form.getSender(),form.getBody().toString(), form.getMessageTo(),form.getReceiver(), form.getCreatedTime());
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