package com.javatechie.cloud.stream.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublisher implements MessagePublisher {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public RedisMessagePublisher(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisMessagePublisher() {
    }

    @Override
    public void publish(Object message,ChannelTopic topic) {
        redisTemplate.convertAndSend(topic.getTopic(),message);
    }
}
