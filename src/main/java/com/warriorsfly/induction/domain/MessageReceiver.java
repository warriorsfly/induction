package com.warriorsfly.induction.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class MessageReceiver<T extends Serializable> {
    private MessageTo type;
    private T receiver;
}
