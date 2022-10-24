package models.entities;

public class User {
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

    public User(String firstName, String lastName, String email, String password, String address, String city, String state, String postalCode, String phoneNumber, String addressAssignAlias, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.addressAssignAlias = addressAssignAlias;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddressAssignAlias() {
        return addressAssignAlias;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "firstName='" + firstName +
                "', lastName='" + lastName +
                "', email='" + email +
                "', password='" + password +
                "', country='" + country +
                "', address='" + address +
                "', city='" + city +
                "', state='" + state +
                "', postalCode='" + postalCode +
                "', phoneNumber='" + phoneNumber +
                "', addressAssignAlias='" + addressAssignAlias + "'";
    }
}
