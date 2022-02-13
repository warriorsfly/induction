package com.warriorsfly.induction.repository.message;

import com.warriorsfly.induction.domain.MessageTo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntity {

    @Id
    private String id;
    /**
     * 消息体
     */
    private String body;
    private String senderId;
    private MessageTo to;
    private Timestamp createdTime;
}
