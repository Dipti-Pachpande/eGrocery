package com.assignment.eGrocery.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemDTO {
    @NotNull(message = "item ID Required")
    private Integer itemId;

    @Min(message = "Quantity should be greater than 0", value = 1)
    private Integer quantity;
}
