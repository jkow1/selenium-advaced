package dataProvider;

import models.entities.User;

public class UserBuilder {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String phoneNumber;
    private String addressAssignAlias;
    private String country;

    public UserBuilder() {
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public UserBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public UserBuilder setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder setAddressAssignAlias(String addressAssignAlias) {
        this.addressAssignAlias = addressAssignAlias;
        return this;
    }

    public UserBuilder setCountry(String country) {
        this.country = country;
        return this;
    }


    public User build() {
        return new User(firstName, lastName, email, password, address, city, state, postalCode, phoneNumber, addressAssignAlias, country);
    }
}
