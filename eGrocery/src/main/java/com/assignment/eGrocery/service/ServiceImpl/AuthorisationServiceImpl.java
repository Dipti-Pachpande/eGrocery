package com.assignment.eGrocery.service.ServiceImpl;

import com.assignment.eGrocery.entity.User;
import com.assignment.eGrocery.exception.GroceryException;
import com.assignment.eGrocery.repo.UsersRepository;
import com.assignment.eGrocery.service.AuthorisationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorisationServiceImpl implements AuthorisationService {

    @Autowired
    UsersRepository usersRepository;
    @Override
    public void authorise(int userId, String role) throws GroceryException {
        Optional<User> optionalUser = usersRepository.findById(userId);
        User user = optionalUser.orElseThrow(()-> new GroceryException("AuthorisationService.UNAUTHORISED"));
        if(!StringUtils.equals(role, user.getUserRole())) {
            throw  new GroceryException("AuthorisationService.UNAUTHORISED");
        }
    }
}
