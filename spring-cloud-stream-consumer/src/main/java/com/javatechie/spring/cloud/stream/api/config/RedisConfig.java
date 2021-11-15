package com.javatechie.spring.cloud.stream.api.config;

import com.javatechie.spring.cloud.stream.api.subscriber.Receiver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory connectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            @Qualifier("notificationListenerAdapter") MessageListenerAdapter notificationListenerAdapter,
                                            @Qualifier("countListenerAdapter") MessageListenerAdapter countListenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(notificationListenerAdapter,  new PatternTopic("notification-events"));
        container.addMessageListener(countListenerAdapter, new PatternTopic("inventory-events"));
        return container;
    }

    @Bean("notificationListenerAdapter")
    MessageListenerAdapter notificationListenerAdapter(Receiver redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveNotificationMessage");
    }

    @Bean("countListenerAdapter")
    MessageListenerAdapter countListenerAdapter(Receiver redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveInventoryMessage");
    }

    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

}
