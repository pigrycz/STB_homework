package TestClasses.JsonPlaceholder_Photos_Crud;

import TestClasses.UrlAndEndpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetPhotosTest {
    @Test
    public void getPhotosTest() {
        Response response = given()
                .when()
                .get(UrlAndEndpoints.getBaseUrl() + UrlAndEndpoints.getPHOTOS())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        List<String> photos = json.getList("id");
        assertEquals(5000, photos.size());
    }

    @Test
    public void getOnePhotoTest(){
        Response response = given()
                .pathParam("photoId", 1)
                .when()
                .get(UrlAndEndpoints.getBaseUrl() + UrlAndEndpoints.getPHOTOS() + "/{photoId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(1, json.getInt("albumId"));
        assertEquals(1, json.getInt("id"));
        assertEquals("accusamus beatae ad facilis cum similique qui sunt", json.get("title"));
        assertEquals("https://via.placeholder.com/600/92c952", json.get("url"));
        assertEquals("https://via.placeholder.com/150/92c952", json.get("thumbnailUrl"));

    }

}