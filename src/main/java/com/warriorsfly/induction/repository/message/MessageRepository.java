package com.warriorsfly.induction.repository.message;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import java.util.UUID;

public interface MessageRepository extends ReactiveCrudRepository<MessageEntity, UUID> { }
