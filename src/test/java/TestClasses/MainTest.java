package TestClasses;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;

public class MainTest {
    public static Faker faker;
    public String fakeTitle;
    public String fakeUrl;
    public String fakeThumbnailUrl;
    public Integer fakeAlbumId;
    public Integer fakeId;

    @BeforeAll
    public static void beforeAll(){
        faker = new Faker();
    }
}