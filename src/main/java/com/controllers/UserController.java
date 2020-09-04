package com.controllers;

import com.entities_auth.User;
import com.repositories.UserRepository;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
  private UserRepository userRepository;
    @Autowired
    private UserService userService;


    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;

    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user){
        userService.createUser(user);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("success" , HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
        Optional<User> user = userService.findByUserId(userId);
        return new ResponseEntity(user , HttpStatus.OK);
    }

    //all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user){
        userService.updateUser(user);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("success" , HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long userId){
        userService.deleteUser(userId);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("success" , HttpStatus.OK);
        return responseEntity;
    }


}