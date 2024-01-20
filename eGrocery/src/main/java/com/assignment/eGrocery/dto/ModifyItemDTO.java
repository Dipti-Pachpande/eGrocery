package com.assignment.eGrocery.dto;

import com.assignment.eGrocery.entity.Item;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModifyItemDTO {
    private double price;
    private String name;

    @NotNull(message = "Auth Details Required")
    private AuthDTO auth;
}
