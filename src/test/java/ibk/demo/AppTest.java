package ibk.demo;

import ibk.demo.data.factory.CreateUserDataFactory;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest
{

    @Test
    public void postLoginTest() {
//        String response = RestAssured
//        RestAssured
//                .given()
                given()
//                .log().uri()
//                .log().body()
//                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post("login")
                .then()
//                .log().all()
//                .extract()
//                .asString();
//                .statusCode(200)
                .statusCode(HttpStatus.SC_OK)
                .body("token", notNullValue());
//        System.out.println(response);



    }
    @Test
    public void getSingleTest(){

        given()
//                .log().all()
//                .contentType(ContentType.JSON)
                .get("users/2")
                .then()
//                .log().all()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }

    @Test
    public void deleteUserTest(){
        given()
                .delete("users/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void patchUserTest(){
        String nameUpdated = given()
                .when()
                .body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}")
                .patch("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getString("name");

        assertThat(nameUpdated).isEqualTo("morpheus");
//        assertThat(name, equalTo("morpheus"));
    }

    @Test
    public void putUserTest(){
        String jobUpdated = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .put("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getString("job");

        assertThat(jobUpdated).isEqualTo("zion resident");
//        assertThat(name, equalTo("morpheus"));
    }

    @Test
    public void getAllUsersTest_1(){

        Response response = given()
                .when()
                .get("users?page=2");

        Headers headers = response.getHeaders();
        int statusCode = response.getStatusCode();
        String body = response.body().asString();
        String contentType = response.getContentType();

        assertThat(statusCode).isEqualTo(HttpStatus.SC_OK);
        System.out.println("**********************");
        System.out.println("body: "+body);
        System.out.println("contentType: "+contentType);
        System.out.println("headers: "+headers.toString());
        System.out.println("**********************");
        System.out.println("contentType"+headers.get("Content-Type"));
        System.out.println("**********************");
    }
    @Test
    public void getAllUsersTest(){

        String response = given()
//                .log().all()
//                .contentType(ContentType.JSON)
                .when()
                .get("users?page=2")
                .then()
//                .log().all()
                .extract().body().asString();

        int page = from(response).get("page");
//        int page =  response.jsonPath().get("page"); --> response tipo Response
        int total_pages = from(response).get("total_pages");
        int idFirstUser = from(response).get("data[0].id");

        System.out.println("page"+page);
        System.out.println("total_pages"+total_pages);
        System.out.println("idFirstUser"+idFirstUser);

        List<Map> usersWithIdGreaterThan10 = from(response).get("data.findAll {user -> user.id > 10}");
        System.out.println(usersWithIdGreaterThan10.get(0).get("email").toString());
        List<Map> user = from(response).get("data.findAll {user -> user.id > 10 && user.last_name == 'Howell'}");
        System.out.println(user.get(0).get("id").toString());
/*        User user = response.body().as(User.class); -> para el caso de crear usuario
        List<User> users = response.jsonPath().getList("", User.class); -> para el caso que retorna de una lista
        List<Data> users = response.jsonPath().getList("data", User.class); -> para el caso que necesites navegar en la respuesta*/
//        http://docs.groovy-lang.org/next/html/documentation/working-with-collections.html#List-Filtering
//        https://docs.groovy-lang.org/latest/html/groovy-jdk/java/util/Collection.html
    }
    @Test
    public void registerUserTest(){
        CreateUserRequest userRequest = CreateUserDataFactory.validUser();
        userRequest.setEmail("eve.holt@reqres.in");
        userRequest.setPassword("pistol");
        CreateUserResponse userResponse =
                given()
                        .when()
                        .body(userRequest)
                        .post("register")
                        .then()
//                        .spec(defaultResponseSpecification())
                        .statusCode(HttpStatus.SC_OK)
                        .contentType(equalTo("application/json; charset=utf-8"))
                        .extract()
                        .body()
                        .as(CreateUserResponse.class);

        assertThat(userResponse.getId()).isEqualTo(4);
        assertThat(userResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
        //antes de aserciones sobre el body, hacer asserios generales como codigo de respuesta contentytype
    }
}
