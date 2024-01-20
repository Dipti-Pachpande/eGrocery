package com.assignment.eGrocery.service;

import com.assignment.eGrocery.dto.UserDTO;
import com.assignment.eGrocery.exception.GroceryException;

public interface UserService {
    String addUser(UserDTO userDTO) throws GroceryException;
    String getRole(int UserId) throws GroceryException;
}
