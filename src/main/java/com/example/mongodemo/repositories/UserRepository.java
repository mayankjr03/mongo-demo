package com.example.mongodemo.repositories;

import com.example.mongodemo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByAddressesStreet(String street);
}