package com.assignment.eGrocery.service.ServiceImpl;

import com.assignment.eGrocery.dto.UserDTO;
import com.assignment.eGrocery.entity.User;
import com.assignment.eGrocery.exception.GroceryException;
import com.assignment.eGrocery.repo.UsersRepository;
import com.assignment.eGrocery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UsersRepository usersRepository;
    @Override
    public String addUser(UserDTO userDTO) throws GroceryException {
        User userEntity = userDTO.userDTOtoEntity();
        try {
            usersRepository.save(userEntity);
        } catch (Exception e) {
            throw new GroceryException("UserService.REGISTRAION_FAILED");
        }

        return "UserAPI.REGISTER_USER_SUCCESS";
    }
}
