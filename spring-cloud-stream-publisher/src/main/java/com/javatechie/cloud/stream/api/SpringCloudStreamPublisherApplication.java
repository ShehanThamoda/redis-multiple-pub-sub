package com.javatechie.cloud.stream.api;

import com.javatechie.cloud.stream.api.dto.OrderDto;
import com.javatechie.cloud.stream.api.service.ManageService;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.connection.SortParameters;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication
public class SpringCloudStreamPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamPublisherApplication.class, args);
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    private ManageService manageService;

    public SpringCloudStreamPublisherApplication(ManageService manageService) {
        this.manageService = manageService;
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void orderBeers() {
//        Flux.interval(Duration.ofSeconds(2))
//                .map(SpringCloudStreamPublisherApplication::toOrder)
//                .doOnEach(o -> manageService.getDetails(o.get()))
//                .subscribe();
//    }
//
//    private static SortParameters.Order toOrder(Long l) {
//        long quantity = l % 5;
//        String name = l % 2 == 0 ? "ale" : "light";
//        return new OrderDto(name, quantity);
//    }
}
