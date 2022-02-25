package com.warriorsfly.col.repository.message;

import com.warriorsfly.col.domain.messages.MessageTo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("messages")
public class MessageEntity {

    @Id
    private Long id;
    /**
     * 消息体
     */
    private String sender;
    private String body;
    private MessageTo message_to;
    private String receiver;
    private Timestamp created_time;
}
