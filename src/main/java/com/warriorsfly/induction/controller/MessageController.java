package com.warriorsfly.induction.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController(value = "/message")
public class MessageController {

//    @PostMapping(value = "/create")
//    private Mono<String> createMessage(String message){
//        return Mono.
//    }
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
//
//    @MessageMapping("/furion")
//    @SendToUser("/topic/message")
//    public Message push(@Payload Message message, Principal user){
//        return message;
//    }

}