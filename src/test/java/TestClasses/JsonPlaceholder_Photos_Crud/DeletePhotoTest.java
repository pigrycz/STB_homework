package TestClasses.JsonPlaceholder_Photos_Crud;

import TestClasses.MainTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static TestClasses.UrlAndEndpoints.BASE_URL;
import static TestClasses.UrlAndEndpoints.PHOTOS;
import static io.restassured.RestAssured.given;

public class DeletePhotoTest extends MainTest {

    @BeforeEach
        public void beforeEach(){
        fakeId = faker.number().numberBetween(1, 5000);
    }


    @Test
    public void deletePhotoTest() {
        Response response = given()
                .pathParam("photoId", fakeId)
                .when()
                .delete(BASE_URL + PHOTOS + "/{photoId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        Assertions.assertEquals(response.asString(), "{}");
    }
}