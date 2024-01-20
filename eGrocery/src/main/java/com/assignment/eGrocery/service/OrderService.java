package com.assignment.eGrocery.service;

import com.assignment.eGrocery.dto.OrderDTO;
import com.assignment.eGrocery.dto.OrderResponseDTO;
import com.assignment.eGrocery.exception.GroceryException;

public interface OrderService {
    public OrderResponseDTO createOrder(OrderDTO orderDTO) throws GroceryException;
}
