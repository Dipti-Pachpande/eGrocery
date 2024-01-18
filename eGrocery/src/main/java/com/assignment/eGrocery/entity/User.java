package com.assignment.eGrocery.entity;

import com.assignment.eGrocery.dto.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fName;
    private String lName;
    private String email;
    private String phoneNumber;
    private String userRole;

    @Column(name = "user_password")
    private String password;

    public UserDTO userEntitiyToDTO() {
        UserDTO user = new UserDTO();
        user.setId(this.id);
        user.setFName(this.fName);
        user.setLName(this.lName);
        user.setUserRole(this.userRole);
        user.setEmail(this.password);
        user.setEmail(this.email);
        user.setPhoneNumber(this.phoneNumber);
        return user;
    }
}
