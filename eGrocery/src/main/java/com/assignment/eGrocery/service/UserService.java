package com.assignment.eGrocery.service;

import com.assignment.eGrocery.dto.UserDTO;
import com.assignment.eGrocery.dto.UserLoginDTO;
import com.assignment.eGrocery.dto.UserLoginResponseDTO;
import com.assignment.eGrocery.dto.UserResponseDTO;
import com.assignment.eGrocery.exception.GroceryException;

public interface UserService {
    UserResponseDTO addUser(UserDTO userDTO) throws GroceryException;
    String getRole(int UserId) throws GroceryException;

    UserLoginResponseDTO login(UserLoginDTO userLoginDTO) throws GroceryException;
}
