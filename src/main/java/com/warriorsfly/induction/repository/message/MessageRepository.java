package com.warriorsfly.induction.repository.message;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepository extends ReactiveCrudRepository<MessageEntity, Long> { }
