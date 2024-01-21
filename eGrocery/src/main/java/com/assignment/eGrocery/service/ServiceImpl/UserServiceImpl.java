package com.assignment.eGrocery.service.ServiceImpl;

import com.assignment.eGrocery.dto.UserDTO;
import com.assignment.eGrocery.dto.UserLoginDTO;
import com.assignment.eGrocery.dto.UserLoginResponseDTO;
import com.assignment.eGrocery.dto.UserResponseDTO;
import com.assignment.eGrocery.entity.User;
import com.assignment.eGrocery.exception.GroceryException;
import com.assignment.eGrocery.repo.UsersRepository;
import com.assignment.eGrocery.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UsersRepository usersRepository;
    @Override
    public UserResponseDTO addUser(UserDTO userDTO) throws GroceryException {
        User user = usersRepository.findByEmail(userDTO.getEmail());
        if(null != user){
            throw new GroceryException("UserService.EMAIL_ALREADY_EXIST");
        }
        User userEntity = userDTO.userDTOtoEntity();
        try {
            userEntity = usersRepository.save(userEntity);
        } catch (Exception e) {
            throw new GroceryException("UserService.REGISTRATION_FAILED");
        }

        return UserResponseDTO.buildUserResponseDTO(userEntity);
    }

    @Override
    public String getRole(int userId) throws GroceryException {
        Optional<User> optionalUser = usersRepository.findById(userId);
        User user =optionalUser.orElseThrow(()-> new GroceryException("UserService.INVALID_USER"));
        return user.getUserRole();
    }

    @Override
    public UserLoginResponseDTO login(UserLoginDTO userLoginDTO) throws GroceryException {
        User user = usersRepository.findByEmail(userLoginDTO.getEmail());
        if(null == user){
            throw new GroceryException("LoginService.EMAIL_NOT_FOUND");
        }
        String password = userLoginDTO.getPassword();
        if(!StringUtils.equals(password, user.getPassword())){
            throw new GroceryException("LoginService.WRONG_PASSWORD");
        }
        return UserLoginResponseDTO.buildUserloginResponseDTO(user);
    }
}
