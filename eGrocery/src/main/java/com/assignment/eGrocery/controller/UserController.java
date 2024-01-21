package com.assignment.eGrocery.controller;

import com.assignment.eGrocery.common.RoleType;
import com.assignment.eGrocery.dto.UserDTO;
import com.assignment.eGrocery.dto.UserLoginDTO;
import com.assignment.eGrocery.dto.UserLoginResponseDTO;
import com.assignment.eGrocery.dto.UserResponseDTO;
import com.assignment.eGrocery.exception.GroceryException;
import com.assignment.eGrocery.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    Environment environment;

    @PostMapping(value = "/addUser" )
    public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserDTO userDTO) throws GroceryException {
        //userService.addUser();
        userDTO.setUserRole(RoleType.USER.name());
        UserResponseDTO userResponseDTO = userService.addUser(userDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/addAdmin" )
    public ResponseEntity<UserResponseDTO> addAdmin(@Valid @RequestBody UserDTO userDTO) throws GroceryException{
        userDTO.setUserRole(RoleType.ADMIN.name());
        UserResponseDTO userResponseDTO  = userService.addUser(userDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) throws GroceryException {
        UserLoginResponseDTO userLoginResponseDTO = userService.login(userLoginDTO);
        return new ResponseEntity<>(userLoginResponseDTO, HttpStatus.OK);
    }
}
