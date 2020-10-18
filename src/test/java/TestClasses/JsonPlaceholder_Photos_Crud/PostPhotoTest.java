package TestClasses.JsonPlaceholder_Photos_Crud;

import TestClasses.MainTest;
import TestClasses.UrlAndEndpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PostPhotoTest extends MainTest {

    @BeforeEach
    public void beforeEach(){
        fakeTitle = faker.lorem().sentence();
        fakeUrl = faker.internet().url();
        fakeThumbnailUrl = faker.internet().url();
        fakeAlbumId = faker.number().numberBetween(1, 100);
    }

    @Test
    public void postPhotoTest(){
        JSONObject photo = new JSONObject();
        photo.put("albumId", fakeAlbumId);
        photo.put("title", fakeTitle);
        photo.put("url", fakeUrl);
        photo.put("thumbnailUrl", fakeThumbnailUrl);

        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .when()
                .post(UrlAndEndpoints.getBaseUrl() + UrlAndEndpoints.getPHOTOS())
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeAlbumId, json.getInt("albumId"));
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeUrl, json.get("url"));
        assertEquals(fakeThumbnailUrl, json.get("thumbnailUrl"));
        assertEquals(5001, json.getInt("id"));
   }
}