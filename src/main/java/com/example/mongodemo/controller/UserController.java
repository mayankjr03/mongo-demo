package com.example.mongodemo.controller;

import com.example.mongodemo.model.User;
import com.example.mongodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addNewUser(user));
    }

    @GetMapping(value = "/findByName")
    public ResponseEntity<List<User>> findByName(@RequestParam String firstName, @RequestParam String lastName) {
        if (firstName == null || lastName == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userService.findByFirstNameLastName(firstName, lastName));
    }

    @GetMapping(value = "/findByStreet")
    private ResponseEntity<List<User>> findUserByStreet(String street) {
        if (null == street || street.equalsIgnoreCase("")) {
            ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userService.findUserByStreet(street));
    }

}