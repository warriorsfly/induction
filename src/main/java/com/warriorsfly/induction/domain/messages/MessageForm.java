package com.warriorsfly.induction.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 消息体
     */
    private Message body;
    private String sender;
    private MessageTo messageTo;
    private String receiver;
    private Timestamp createdTime;
}
