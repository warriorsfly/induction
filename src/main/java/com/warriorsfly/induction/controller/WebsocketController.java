package com.warriorsfly.induction.controller;

import com.warriorsfly.induction.domain.messages.Message;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebsocketController implements StreamListener<String, ObjectRecord<String, Message>> {
    @Override
    public void onMessage(ObjectRecord<String, Message> message) {
//        if(message instanceof )

    }

    @MessageMapping("/broadcast")
    @SendTo("/topic/broadcast")
    public Message greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return message;
    }

    @MessageMapping("/chat")
    @SendToUser("/topic/chat")
    public Message push(@Payload Message message, Principal user){
        return message;
    }

}