package com.assignment.eGrocery.controller;

import com.assignment.eGrocery.dto.OrderDTO;
import com.assignment.eGrocery.dto.OrderResponseDTO;
import com.assignment.eGrocery.exception.GroceryException;
import com.assignment.eGrocery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    Environment environment;

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> orderItems(@RequestBody OrderDTO orderDTO) throws GroceryException {
        orderService.createOrder(orderDTO);
        return null;
    }
}
