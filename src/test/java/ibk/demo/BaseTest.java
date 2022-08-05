package ibk.demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    private static final Logger logger = LogManager.getLogger(AppTest.class);
    @BeforeClass
    public static void setUp(){
        logger.info("iniciado la configuración");
//        RestAssured.baseURI = "https://reqres.in";
//        RestAssured.basePath = "/api";
//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//        RestAssured.requestSpecification = new RequestSpecBuilder()
//                .setContentType(ContentType.JSON)
//                .build();
        RestAssured.requestSpecification = defaultRequestSpecification();
        logger.info("onfiguración exitosa");

    }

    private static RequestSpecification defaultRequestSpecification(){
        List<Filter> filters = new ArrayList<>();
        filters.add(new RequestLoggingFilter());
        filters.add(new ResponseLoggingFilter());

//        return new RequestSpecBuilder().setBaseUri("https://reqres.in")
        return new RequestSpecBuilder().setBaseUri(ConfVariables.getHost())
//                .setBasePath("/api")
                .setBasePath(ConfVariables.getMPath())
                .addFilters(filters)
                .setContentType(ContentType.JSON).build();

    }

    public ResponseSpecification defaultResponseSpecification(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_OK)
                .build();

    }
}
