package com.assignment.eGrocery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponseDTO {
    private Integer id;
    private Integer itemId;
    private String name;
    private Integer quantity;
    private Double cost;

}
