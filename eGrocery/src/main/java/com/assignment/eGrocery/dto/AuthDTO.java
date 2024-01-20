package com.assignment.eGrocery.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthDTO {
    @NotNull(message = "User ID Required")
    private int userId;

}

