package com.assignment.eGrocery.service;

import com.assignment.eGrocery.exception.GroceryException;

public interface AuthorisationService {
    public void authorise(int userId, String role) throws GroceryException;
}
