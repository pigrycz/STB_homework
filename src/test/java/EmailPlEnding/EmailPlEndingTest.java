package EmailPlEnding;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class EmailPlEndingTest {
    @Test
    public void emailPlEnding(){
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        List<String> emails = json.getList("email");

        List<String> plEmails = emails.stream().filter(email -> email.contains(".pl")).collect(Collectors.toList());

        Assertions.assertEquals(plEmails.size(), 0);
    }
}
