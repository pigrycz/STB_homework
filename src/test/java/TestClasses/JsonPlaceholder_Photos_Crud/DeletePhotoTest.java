package TestClasses.JsonPlaceholder_Photos_Crud;

import TestClasses.MainTest;
import TestClasses.UrlAndEndpoints;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
                .delete(UrlAndEndpoints.getBaseUrl() + UrlAndEndpoints.getPHOTOS() + "/{photoId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        Assertions.assertEquals(response.asString(), "{}");
    }
}