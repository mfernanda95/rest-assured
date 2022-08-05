package ibk.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ibk.demo.data.factory.CreateUserDataFactory;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqRes2Tests extends BaseTest {
    @Test
    public void getSingleTest() throws JsonProcessingException {

        CreateUserRequest createUserRequest= CreateUserDataFactory.missingAllInformation();
        ObjectMapper objectMapper = new ObjectMapper();
        CreateUserRequest userWithInvalidEmail= CreateUserDataFactory.userWithInvalidEmail();

    System.out.println(objectMapper.writeValueAsString(userWithInvalidEmail));
//        given()
//                .when()
//                .get("users/2")
//                .then()
//                .statusCode(HttpStatus.SC_OK)
//                .body("data.id", equalTo(2));
    }


//    System.out.println(objectMapper.w)
    //mvn test -Denv=local
    //mvn test -Dhost=qwqeqweqwe
}
