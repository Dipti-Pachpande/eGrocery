package com.assignment.eGrocery.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class InventoryDTO {
    @NotNull(message = "Auth details are required")
    private AuthDTO auth;
    @NotNull(message = "Quantity is required")
    private int quantity;

    @Pattern(regexp = "ADD|REMOVE|UPDATE", message = "Invalid Operation")
    private String operation;
}
