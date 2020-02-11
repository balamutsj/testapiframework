package ApiData;

import com.google.gson.Gson;

public class ApiUtils {
    public static Object getPropertyFromResponseBody(Class objectClass, String responseBody) {
        Gson gson = new Gson();
        return gson.fromJson(responseBody, objectClass);
    }

    public static String generateRandomString(int count) {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}
