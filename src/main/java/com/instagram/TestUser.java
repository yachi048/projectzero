package com.instagram;

import com.instagram.controller.UserController;
import com.instagram.model.User;
import com.instagram.service.UserService;
import com.instagram.service.UserServiceImpl;

import java.util.List;

public class TestUser {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        UserController userController = new UserController(userService);

        // =====================================================
        // 1. REGISTER USER
        // =====================================================

        User user = new User();

        user.setUsername("testuser2");
        user.setEmail("testuser2@gmail.com");
        user.setPasswordHash("password123");
        user.setStatus("ACTIVE");
        user.setRole("USER");

        boolean registered = userController.registerUser(user);

        System.out.println("1. Register: "
                + (registered ? "PASS" : "FAIL"));


        // =====================================================
        // 2. DUPLICATE USERNAME
        // =====================================================

        User duplicateUser = new User();

        duplicateUser.setUsername("testuser2");
        duplicateUser.setEmail("different@gmail.com");
        duplicateUser.setPasswordHash("password456");
        duplicateUser.setStatus("ACTIVE");
        duplicateUser.setRole("USER");

        boolean duplicateResult =
                userController.registerUser(duplicateUser);

        System.out.println("2. Duplicate username blocked: "
                + (!duplicateResult ? "PASS" : "FAIL"));


        // =====================================================
        // 3. LOGIN WITH CORRECT PASSWORD
        // =====================================================

        User loggedInUser =
                userController.login("testuser2", "password123");

        System.out.println("3. Login correct password: "
                + (loggedInUser != null ? "PASS" : "FAIL"));


        // =====================================================
        // 4. LOGIN WITH WRONG PASSWORD
        // =====================================================

        User wrongLogin =
                userController.login("testuser2", "wrongpassword");

        System.out.println("4. Login wrong password blocked: "
                + (wrongLogin == null ? "PASS" : "FAIL"));


        // =====================================================
        // 5. GET USER BY ID
        // =====================================================

        User savedUser =
                userController.getUserByUsername("testuser2");

        if (savedUser != null) {

            int userId = savedUser.getUserId();

            User userById =
                    userController.getUserById(userId);

            System.out.println("5. Get by ID: "
                    + (userById != null ? "PASS" : "FAIL"));

        } else {

            System.out.println("5. Get by ID: FAIL");
        }


        // =====================================================
        // 6. GET USER BY USERNAME
        // =====================================================

        User userByUsername =
                userController.getUserByUsername("testuser2");

        System.out.println("6. Get by username: "
                + (userByUsername != null ? "PASS" : "FAIL"));


        // =====================================================
        // 7. UPDATE USER
        // =====================================================

        if (userByUsername != null) {

            userByUsername.setEmail("updated2@gmail.com");
            userByUsername.setPasswordHash("newpassword123");
            userByUsername.setStatus("ACTIVE");
            userByUsername.setRole("USER");

            boolean updated =
                    userController.updateUser(userByUsername);

            System.out.println("7. Update user: "
                    + (updated ? "PASS" : "FAIL"));

        } else {

            System.out.println("7. Update user: FAIL");
        }


        // =====================================================
        // 8. DEACTIVATE USER
        // =====================================================

        User userBeforeDeactivate =
                userController.getUserByUsername("testuser2");

        if (userBeforeDeactivate != null) {

            int userId =
                    userBeforeDeactivate.getUserId();

            boolean deactivated =
                    userController.deactivateUser(userId);

            System.out.println("8. Deactivate: "
                    + (deactivated ? "PASS" : "FAIL"));

        } else {

            System.out.println("8. Deactivate: FAIL");
        }


        // =====================================================
        // 9. LOGIN AFTER DEACTIVATION
        // =====================================================

        User inactiveLogin =
                userController.login(
                        "testuser2",
                        "newpassword123"
                );

        System.out.println("9. Login blocked after deactivate: "
                + (inactiveLogin == null ? "PASS" : "FAIL"));


        // =====================================================
        // 10. GET ALL USERS
        // =====================================================

        List<User> users =
                userController.getAllUsers();

        System.out.println("10. Total users in DB: "
                + users.size());
    }
}