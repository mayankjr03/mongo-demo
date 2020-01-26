package com.example.mongodemo.service;

import com.example.mongodemo.model.User;
import com.example.mongodemo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * Created by mayank on 24/01/20
 */
@Service
public class UserService {

    final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public User addNewUser(User user) {
        LOG.debug("Saving user :" + user);
        return userRepository.save(user);
    }

    public List<User> findUserByStreet(String street) {
        /*Aggregation agg = newAggregation(
                unwind("addresses"),
                match(Criteria.where("addresses.street").is(street)),
                project("firstName").and("lastName").previousOperation()
        ).withOptions(newAggregationOptions().collation(Collation.of("en").strength(Collation.ComparisonLevel.secondary()
                .excludeCase())).build());
        AggregationResults<User> groupResults
                = mongoTemplate.aggregate(agg, User.COLLECTION_NAME, User.class);
        return groupResults.getMappedResults();*/
        return userRepository.findByAddressesStreet(street);
    }

    public List<User> getAllUsers() {
        LOG.debug("Getting all users.");
        return userRepository.findAll();
    }

    public List<User> findByFirstNameLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
