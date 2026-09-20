package com.instagram.util;

import com.instagram.model.User;
import com.instagram.service.UserService;
import com.instagram.service.UserServiceImpl;

public class UserTest {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        User user = new User();

        user.setUsername("monu123");
        user.setEmail("monu123@gmail.com");
        user.setPasswordHash("password123");
        user.setStatus("ACTIVE");
        user.setRole("USER");

        boolean result = userService.registerUser(user);

        if (result) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user.");
        }
    }
}