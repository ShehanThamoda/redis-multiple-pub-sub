package com.javatechie.cloud.stream.api.config;

import org.springframework.data.redis.listener.ChannelTopic;

public interface MessagePublisher {
    void publish(Object message, ChannelTopic topic);
}
