package com.warriorsfly.induction.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class Message<T extends Serializable,R extends Serializable>
        implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID id;
    /**
     * 消息体
     */
    private T body;

    private User sender;

    private MessageReceiver<R> receiver;

    private Timestamp createdTime;
}
