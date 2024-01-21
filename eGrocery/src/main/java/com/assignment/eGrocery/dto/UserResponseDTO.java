package com.assignment.eGrocery.dto;

import com.assignment.eGrocery.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public static UserResponseDTO buildUserResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFName())
                .lastName(user.getLName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
