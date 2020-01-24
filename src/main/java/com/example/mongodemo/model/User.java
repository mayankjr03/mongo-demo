package com.example.mongodemo.model;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by mayank on 24/01/20
 */
@Document(collection = User.COLLECTION_NAME, collation = "{ locale: 'en', strength: 2 }")
@CompoundIndexes({
        @CompoundIndex(def = "{'addresses.street':1}", name = "address_street")
})
public class User {

    public static final String COLLECTION_NAME = "user";

    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

    @Indexed
    private List<Address> addresses;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
