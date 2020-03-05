package ApiData;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;

public class ApiUtils {
    public static Object getPropertyFromResponseBody(Class objectClass, String responseBody) {
        Gson gson = new Gson();
        return gson.fromJson(responseBody, objectClass);
    }

    public static String generateRandomString(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }


}
