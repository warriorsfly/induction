package com.warriorsfly.induction.repository;

import com.warriorsfly.induction.domain.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface MessageRepository extends ReactiveCrudRepository<Message, UUID> {}
