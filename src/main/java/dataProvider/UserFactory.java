package dataProvider;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.extern.slf4j.Slf4j;
import models.User;
import models.UserBuilder;

import java.util.Locale;

@Slf4j
public class UserFactory {

    Locale locale = new Locale("en-US");
    private Faker faker = new Faker(locale);

    public User getRandomUser() {
        Faker faker = this.faker;
        User randomUser = new UserBuilder().setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(getRandomEmail())
                .setPassword(getRandomPassword())
                .setAddress(faker.address().streetAddress())
                .setCity(faker.address().city())
                .setState(faker.address().state())
                .setPostalCode(faker.address().zipCode())
                .setPhoneNumber(faker.phoneNumber().phoneNumber())
                .setAddressAssignAlias(getRandomPassword())
                .setCountry("United States")
                .build();
        log.info("Random user data: {}", randomUser.toString());
        return randomUser;
    }

    public User getAlreadyExistingUser() {
        User existingUser = new UserBuilder().setFirstName("Jan")
                .setLastName("Nowak")
                .setEmail("testOfCheckout00@test.sii.pl")
                .setPassword("password")
                .setAddress(faker.address().streetAddress())
                .setCity(faker.address().city())
                .setState(faker.address().state())
                .setPostalCode("11111")
                .setPhoneNumber(faker.phoneNumber().phoneNumber())
                .setAddressAssignAlias(getRandomPassword())
                .setCountry("United States")
                .build();
        log.info("Existing user data: {}", existingUser.toString());
        return existingUser;
    }

    private String getRandomEmail() {
        FakeValuesService fakeValuesService = new FakeValuesService(locale, new RandomService());
        return fakeValuesService.bothify("test##@????.sii.pl");
    }

    private String getRandomPassword() {
        return new FakeValuesService(locale, new RandomService()).bothify("###???");
    }

}
