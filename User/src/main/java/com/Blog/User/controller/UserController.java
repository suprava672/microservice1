package com.Blog.User.controller;

import com.Blog.User.entity.User;
import com.Blog.User.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity(user, HttpStatus.CREATED);

    }

    @GetMapping
    @CircuitBreaker(name = "bookServiceCircuitBreaker", fallbackMethod = "fallbackGetAllUser")
    public List<User> getAllUser() {
        List<User> userList = userService.getAllUser();
        return userList;
    }

    // Fallback method
    public ResponseEntity<List<User>> fallbackGetAllUser(Throwable throwable) {
        // Log the error (optional)
        System.out.println("Fallback method triggered. Error: " + throwable.getMessage());

        // Create a dummy user as a fallback
        User dummyUser = User.builder()
                .email("dummy@gmail.com")
                
                .name("Dummy")
                .build();

        // Wrap the dummy user in a list
        List<User> userList = Collections.singletonList(dummyUser);

        // Return the list wrapped in a ResponseEntity with BAD_REQUEST status
        return new ResponseEntity<>(userList, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
        //changes done

    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        String s = userService.deleteUserById(id);
        return s;
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        User newUser = userService.updateUser(user, id);
        return newUser;

    }
//changes is not done

}
