package com.warriorsfly.induction.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消息体
     */
    private String title;

    private String description;

    private String memo;
}
