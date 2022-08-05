package ibk.demo.data.factory;

import com.github.javafaker.Faker;
import ibk.demo.CreateUserRequest;
import org.apache.commons.lang3.StringUtils;

import static ibk.demo.UserRequestBuilder.aUser;

public class CreateUserDataFactory {
    private static final Faker faker = new Faker();
    private static final String DEFAULT_USERNAME = "eve.holt@reqres.in";
    public static CreateUserRequest missingAllInformation(){
        return aUser()
                .withEmail(StringUtils.EMPTY)
                .withPassword(StringUtils.EMPTY)
                .build();
    }

    public static CreateUserRequest nullInformation(){
        return aUser()
                .withEmail(null)
                .withPassword(null)
                .build();
    }

    public static CreateUserRequest validUser(){
        return aUser()
                .withEmail(DEFAULT_USERNAME)
                .withPassword(faker.internet().password())
                .build();
    }

    public static CreateUserRequest userWithInvalidEmail(){
        return aUser()
                .withEmail(faker.internet().emailAddress())
                .withPassword(faker.internet().password())
                .build();
    }
}
