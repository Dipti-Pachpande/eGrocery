package com.assignment.eGrocery.dto;

import com.assignment.eGrocery.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDTO {
    private int userId;

    public static UserLoginResponseDTO buildUserloginResponseDTO(User user){
        return UserLoginResponseDTO.builder()
                .userId(user.getId()).build();
    }
}
