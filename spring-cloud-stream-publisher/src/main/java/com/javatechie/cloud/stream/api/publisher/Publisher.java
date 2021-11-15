package com.javatechie.cloud.stream.api.publisher;

import com.javatechie.cloud.stream.api.config.RedisMessagePublisher;
import com.javatechie.cloud.stream.api.dto.OrderDto;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Publisher {

    @Autowired
    private RedisMessagePublisher messagePublisher;

    @PostMapping("/order")
    @Timed(value = "greeting.time", description = "Time taken to return greeting")
    public String publish(@RequestBody OrderDto product){
        log.info(">> publishing : {}", product.toString());
        messagePublisher.publish(product,new ChannelTopic("notification-events"));
        messagePublisher.publish(product,new ChannelTopic("inventory-events"));
        return "Success";
    }
}
