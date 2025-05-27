package com.example.assignment1.controller;


import com.example.assignment1.Response;
import com.example.assignment1.model.User;
import com.example.assignment1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //log in
    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");

        System.out.println("\n[INFO] - Login attempt with username: " + username + " and password: " + password + "\n");
        if(username == null || password == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        Response response = userService.login(username, password);

        if(response.isSuccess()){
            return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.getMessage());
        }
    }

    //create user
    @RequestMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user){
        System.out.println("\n[INFO] - Create user attempt with username: " + user.toString() + "\n");
        if(user.getUsername() == null || user.getPassword() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password, try again.");
        }
        Response response = userService.createUser(user);
        if(response.isSuccess()){
            return ResponseEntity.status(HttpStatus.CREATED).body(response.getMessage());
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response.getMessage());
        }
    }

    //delete user
    @RequestMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody String username){
        System.out.println("\n[INFO] - Delete user attempt with username: " + username + "\n");
        if(username == null || username.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username, try again.");
        }
        Response response = userService.deleteUser(username);
        if(response.isSuccess()){
            return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.getMessage());
        }
    }

    //update user
    @RequestMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        System.out.println("\n[INFO] - Update user attempt with username: " + user.toString() + "\n");
        if(user.getUsername() == null || user.getPassword() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password, try again.");
        }
        Response response = userService.updateUser(user);
        if(response.isSuccess()){
            return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.getMessage());
        }
    }
}
