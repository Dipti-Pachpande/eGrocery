package com.assignment.eGrocery.dto;

import lombok.Data;

@Data
public class OrderItemResponseDTO {
    private Integer itemId;
    private Integer quantity;
    private Double cost;

}
