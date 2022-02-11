package com.warriorsfly.induction.configuration;

import com.warriorsfly.induction.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
public class RedisConfig {

    @Value("${stream.key:medical-events}")
    private String key;


    @Autowired
    private StreamListener<String, ObjectRecord<String, Message>> listener;

    @Bean
    public Subscription subscription(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        var options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                .targetType(Message.class)
                .build();
        var container = StreamMessageListenerContainer
                .create(redisConnectionFactory, options);
        var subscription = container.receiveAutoAck(
                Consumer.from(key, InetAddress.getLocalHost().getHostName()),
                StreamOffset.create(key, ReadOffset.lastConsumed()),
                listener);
        container.start();
        return subscription;
    }
}
