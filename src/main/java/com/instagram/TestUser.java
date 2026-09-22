package com.instagram;
import com.instagram.controller.UserController;
import com.instagram.model.User;
import com.instagram.service.UserServiceImpl;
public class TestUser {
        public static void main(String[] args) {
            UserController controller = new UserController(new UserServiceImpl());

            // 1. Register
            User u = new User();
            u.setUsername("testuser1");
            u.setEmail("testuser1@mail.com");
            u.setPasswordHash("MyPass123");   // plain password, gets hashed inside
            boolean registered = controller.registerUser(u);
            System.out.println("1. Register: " + (registered ? "PASS" : "FAIL"));

            // 2. Duplicate username should fail
            User dup = new User();
            dup.setUsername("testuser1");
            dup.setEmail("different@mail.com");
            dup.setPasswordHash("AnotherPass1");
            boolean dupResult = controller.registerUser(dup);
            System.out.println("2. Duplicate username blocked: " + (!dupResult ? "PASS" : "FAIL"));

            // 3. Login with correct password
            User loginOk = controller.login("testuser1", "MyPass123");
            System.out.println("3. Login correct password: " + (loginOk != null ? "PASS" : "FAIL"));

            // 4. Login with wrong password should fail
            User loginBad = controller.login("testuser1", "wrongPassword");
            System.out.println("4. Login wrong password blocked: " + (loginBad == null ? "PASS" : "FAIL"));

            // 5. Get by ID
            if (loginOk != null) {
                User byId = controller.getUserById(loginOk.getUserId());
                System.out.println("5. Get by ID: " + (byId != null && byId.getUsername().equals("testuser1") ? "PASS" : "FAIL"));
            }

            // 6. Get by username
            User byUsername = controller.getUserByUsername("testuser1");
            System.out.println("6. Get by username: " + (byUsername != null ? "PASS" : "FAIL"));

            // 7. Update
            if (loginOk != null) {
                loginOk.setEmail("updated@mail.com");
                boolean updated = controller.updateUser(loginOk);
                System.out.println("7. Update user: " + (updated ? "PASS" : "FAIL"));
            }

            // 8. Deactivate
            if (loginOk != null) {
                boolean deactivated = controller.deactivateUser(loginOk.getUserId());
                System.out.println("8. Deactivate: " + (deactivated ? "PASS" : "FAIL"));

                // 9. Login after deactivation should fail
                User loginAfterDeactivate = controller.login("testuser1", "MyPass123");
                System.out.println("9. Login blocked after deactivate: " + (loginAfterDeactivate == null ? "PASS" : "FAIL"));
            }

            // 10. Get all users
            System.out.println("10. Total users in DB: " + controller.getAllUsers().size());
        }

}
