package com.example.mongodemo.service;

import com.example.mongodemo.MongoDemoApplication;
import com.example.mongodemo.model.Address;
import com.example.mongodemo.model.User;
import com.example.mongodemo.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Collections;

/**
 * Created by mayank on 24/01/20
 */
@SpringBootTest(
        classes = MongoDemoApplication.class,
        webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void setup() {
        User user = new User();
        user.setFirstName("Mayank");
        user.setLastName("Rupareliya");
        Address address = new Address();
        address.setStreet("My Street");
        user.setAddresses(Collections.singletonList(address));

        userRepository.save(user);
    }

    @Test
    @DisplayName("Getting all users")
    void testFindAll() {
        Assert.notEmpty(userService.getAllUsers(), "One user must be there in db");
    }

    @Test
    @DisplayName("Find user by firstName and lastName")
    void testFindByName() {
        Assert.notEmpty(userService.findByFirstNameLastName("mayank","rupareliyA"),
                "Can't search user ignoring case");
        Assert.notEmpty(userService.findByFirstNameLastName("MAYANK","RUPARELIYA"),
                "Can't search user ignoring case");
    }

    @Test
    @DisplayName("Find user by user's address's street")
    void testFindByStreet() {
        Assert.notEmpty(userService.findUserByStreet("my street"),
                "Can't search user with street");
        Assert.notEmpty(userService.findUserByStreet("MY STREET"),
                "Can't search user with street");
    }

    @AfterAll
    void cleanUp() {
        userRepository.deleteAll();
    }
}
