package com.assignment.eGrocery.dto;

import lombok.Data;

import java.util.List;
@Data
public class OrderResponseDTO {
    private Integer orderId;
    private Double totalCost;
    private List<OrderItemResponseDTO> orderItems;

}
