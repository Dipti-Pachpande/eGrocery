package com.assignment.eGrocery.controller;

import com.assignment.eGrocery.common.RoleType;
import com.assignment.eGrocery.dto.UserDTO;
import com.assignment.eGrocery.exception.GroceryException;
import com.assignment.eGrocery.repo.UsersRepository;
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
    UsersRepository usersRepo;
    @Autowired
    UserService userService;
    @Autowired
    Environment environment;


    /*@PostMapping(value = "/addUser")
    public String addUser(){
        User user = new User();
        user.setFName("test");
        usersRepo.save(user);
        return "success";
    }*/

    @PostMapping(value = "/addUser" )
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO userDTO) throws GroceryException {
        //userService.addUser();
        userDTO.setUserRole(RoleType.USER.name());
        String message = userService.addUser(userDTO);
        return new ResponseEntity<>(environment.getProperty(message), HttpStatus.CREATED);
    }

    @PostMapping(value = "/addAdmin" )
    public ResponseEntity<String> addAdmin(@Valid @RequestBody UserDTO userDTO) throws Exception{
        userDTO.setUserRole(RoleType.ADMIN.name());
        String message = userService.addUser(userDTO);
        return new ResponseEntity<>(environment.getProperty(message), HttpStatus.CREATED);
    }

}
