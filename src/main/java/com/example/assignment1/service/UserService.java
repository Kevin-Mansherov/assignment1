package com.example.assignment1.service;


import com.example.assignment1.Response;
import com.example.assignment1.model.User;
import com.example.assignment1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //check if user exists for login
    public Response login(String username,String password) {
        Response response = null;
        Optional<User> user = Optional.of(userRepository.getById(username));

        //check if user  exists
        if(!user.isPresent()){
            response = new Response(false,"user does not exists, create a new user."); //user does not exist
        }
        //check if password is correct
        else if(user.get().getPassword().equals(password)){
            response = new Response(true,"logged in");//user exists and password is correct
        }
        else{
            response = new Response(false,"incorrect password, try again.");//user exists but password is incorrect
        }
        return response;
    }

    //create user
    public Response createUser(User user){
        Response response = null;
        //check if username already exists
        if(userRepository.existsById(user.getUsername())) {
            response = new Response(false,"username already exists, try a different username."); //username already exists
        }

        //check if the password is already in the database
        List<User> users = userRepository.findAll();
        if(users.stream().anyMatch(u -> u.getPassword().equals(user.getPassword()))) {
            response = new Response(false,"password already exists, try a different password."); //password already exists
        } else {
            userRepository.save(user); //save the new user
            response = new Response(true,"user created successfully"); //user created successfully
        }
        return response;
    }

    //delete user
    public Response deleteUser(String username){
        //check if user exists
        Optional<User> user = userRepository.findById(username);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return new Response(true,"user deleted successfully."); //user deleted successfully
        } else {
            return new Response(false,"user does not exists."); //user does not exist
        }
    }

    //update user
    public Response updateUser(User user){
        Response response = null;
        //check if user exists
        Optional<User> existingUser = userRepository.findById(user.getUsername());
        if(existingUser.isPresent()){
            User userToUpdate = existingUser.get();
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setRoles(user.getRoles());
            userRepository.save(userToUpdate);
            response = new Response(true,"user updated successfully."); //user updated successfully
        } else {
            response = new Response(false,"user does not exists."); //user does not exist
        }
        return response;
    }
}
