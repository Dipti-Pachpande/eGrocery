package com.assignment.eGrocery.repo;

import com.assignment.eGrocery.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "userRepo")
public interface UsersRepository extends CrudRepository<User, Integer> {
    public User findByEmail(String email);
}
