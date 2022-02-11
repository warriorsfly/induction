package com.warriorsfly.induction.repository.message;

import com.warriorsfly.induction.domain.MessageTo;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.util.UUID;
@Data
public class MessageEntity {

    @Id
    private UUID id;
    /**
     * 消息体
     */
    private String body;
    private UUID senderId;
    private MessageTo to;
    private Timestamp createdTime;
}
