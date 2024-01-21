package com.assignment.eGrocery.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDTO {
    @NotNull (message = "Email Required")
    private String email;

    @NotNull(message = "Password Required")
    private String password;
}
