package com.warriorsfly.induction.controller;

import com.warriorsfly.induction.domain.messages.Message;
import com.warriorsfly.induction.domain.messages.MessageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageConsumerController implements StreamListener<String, ObjectRecord<String, Message>> {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void onMessage(ObjectRecord<String, Message> message) {


    }

    private void sendNotification(String username, MessageForm message){
        template.convertAndSendToUser(username,"/queue/notifications",message.getBody());
    }
    private void sendBroadcast(MessageForm message){
        template.convertAndSend("/queue/broadcasts:"+message.getReceiver(),message.getBody());
    }

}