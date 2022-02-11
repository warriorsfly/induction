package com.warriorsfly.induction.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
