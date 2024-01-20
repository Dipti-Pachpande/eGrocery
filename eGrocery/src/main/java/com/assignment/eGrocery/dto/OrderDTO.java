package com.assignment.eGrocery.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Integer userId;
    private List<OrderItemDTO> orderItems;
}
