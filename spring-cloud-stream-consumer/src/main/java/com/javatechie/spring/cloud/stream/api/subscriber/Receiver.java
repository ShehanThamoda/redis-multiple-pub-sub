package com.javatechie.spring.cloud.stream.api.subscriber;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Receiver {

    public void receiveNotificationMessage(String message){

        log.info("Consume from notification topic: {}",message);
    }
    public void receiveInventoryMessage(String message){
        log.info("Consume from inventory topic: {}",message);
    }

}
