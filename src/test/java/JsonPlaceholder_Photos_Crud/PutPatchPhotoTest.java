package JsonPlaceholder_Photos_Crud;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PutPatchPhotoTest {

    private static Faker faker;
    private String fakeTitle;
    private String fakeUrl;
    private String fakeThumbnailUrl;
    private Integer fakeAlbumId;
    private Integer fakeId;

    @BeforeAll
    public static void beforeAll() {
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach() {
            fakeTitle = faker.lorem().sentence();
            fakeUrl = faker.internet().url();
            fakeThumbnailUrl = faker.internet().url();
            fakeAlbumId = faker.number().numberBetween(1, 100);
            fakeId = faker.number().numberBetween(1, 5000);
    }


    @Test
    public void putPhotoTest() {
        JSONObject photo = new JSONObject();
        photo.put("albumId", fakeAlbumId);
        photo.put("title", fakeTitle);
        photo.put("url", fakeUrl);
        photo.put("thumbnailUrl", fakeThumbnailUrl);

        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .pathParam("photoId", fakeId)
                .when()
                .put(Address.getAddress() + "/{photoId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeAlbumId, json.getInt("albumId"));
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeUrl, json.get("url"));
        assertEquals(fakeThumbnailUrl, json.get("thumbnailUrl"));
        assertEquals(fakeId, json.getInt("id"));
    }

    @Test
    public void patchPhotoTest() {
        JSONObject photo = new JSONObject();
        photo.put("title", fakeTitle);
        photo.put("url", fakeUrl);

        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .pathParam("photoId", fakeId)
                .when()
                .put(Address.getAddress() + "/{photoId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeUrl, json.get("url"));
        assertEquals(fakeId, json.getInt("id"));
    }
}
