package com.assignment.eGrocery.dto;

import com.assignment.eGrocery.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {
    private int id;
    @NotNull(message = "First Name Required")
    private String fName;

    @NotNull (message = "Last Name Required")
    private String lName;

    @NotNull (message = "Email Required")
    @Pattern(regexp = "[A-z0-9._]+@[A-z]{2,}\\.[A-z][A-z.]+", message = "Invalid email format.")
    private String email;

    @NotNull(message = "Phone Number Required")
    @Pattern(regexp = "[6-9][0-9]{9}", message = "Invalid Phone Number Format.")
    private String phoneNumber;

    private String userRole;

    @NotNull(message = "Password Required")
    @Pattern(regexp = ".*[A-Z]+.*", message = "Uppercase character Required.")
    @Pattern(regexp = ".*[0-9]+.*", message = "Numeric character Required.")
    @Pattern(regexp = ".*[a-z]+.*", message = "Lowercase character Required.")
    @Pattern(regexp = ".*[!@#$%^&*]+.*", message = "Special character Required.")
    private String password;

    public User userDTOtoEntity() {
        User user = new User();
        user.setId(this.id);
        user.setFName(this.fName);
        user.setLName(this.lName);
        user.setUserRole(this.userRole);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setPhoneNumber(this.phoneNumber);
        return user;
    }
}
