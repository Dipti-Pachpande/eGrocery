package com.assignment.eGrocery.service;

import com.assignment.eGrocery.dto.UserDTO;
import com.assignment.eGrocery.exception.GroceryException;

public interface UserService {
    public String addUser(UserDTO userDTO) throws GroceryException;
}
