package com.warriorsfly.induction.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Message {
    @Id
    private UUID id;

    private String body;
}
