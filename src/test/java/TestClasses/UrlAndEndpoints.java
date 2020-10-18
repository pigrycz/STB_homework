package TestClasses;

public class UrlAndEndpoints {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
            private static final String PHOTOS = "photos";
            private static final String USERS = "users";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getPHOTOS() {
        return PHOTOS;
    }

    public static String getUSERS() {
        return USERS;
    }
}
