package TestClasses.EmailPlEnding;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static TestClasses.UrlAndEndpoints.BASE_URL;
import static TestClasses.UrlAndEndpoints.USERS;
import static io.restassured.RestAssured.given;

public class EmailPlEndingTest {
    private CharSequence suffix = ".pl";

    @Test
    public void emailPlEnding(){
        Response response = given()
                .when()
                .get(BASE_URL + USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        List<String> emails = json.getList("email");

        List<String> plEmails = emails.stream().filter(email -> email.contains(suffix)).collect(Collectors.toList());

        Assertions.assertEquals(plEmails.size(), 0);
    }
}
