package com.assignment.eGrocery.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    @NotNull(message = "User ID Required")
    private Integer userId;

    @NotNull(message = "At least One OrderItem Required")
    private List<OrderItemDTO> orderItems;
}
