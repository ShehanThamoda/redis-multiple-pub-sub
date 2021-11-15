package com.javatechie.cloud.stream.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OrderDto implements Serializable {
    private String name;
    private long quantity;

    public OrderDto(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
