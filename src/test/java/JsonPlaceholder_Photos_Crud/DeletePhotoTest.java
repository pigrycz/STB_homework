package JsonPlaceholder_Photos_Crud;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeletePhotoTest {
    private static Faker faker;
    private Integer fakeId;


    @BeforeAll
        public static void beforeAll() {
        faker = new Faker();
    }

    @BeforeEach
        public void beforeEach(){
        fakeId = faker.number().numberBetween(1, 5000);
    }


    @Test
    public void deletePhotoTest() {
        Response response = given()
                .pathParam("photoId", fakeId)
                .when()
                .delete(Address.getAddress() + "/{photoId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        Assertions.assertEquals(response.asString(), "{}");
    }
}
