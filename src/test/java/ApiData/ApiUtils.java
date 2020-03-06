package ApiData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Type;
import java.util.List;

public class ApiUtils {
    public static Object fromJsonObject(Class objectClass, String responseBody) {
        Gson gson = new Gson();
        return gson.fromJson(responseBody, objectClass);
    }

    public static <T> List<T> fromJsonArrayList(String json, Class<T> myType) {
        Gson gson = new Gson();
        Type collectionType = TypeToken.getParameterized(List.class, myType).getType();
        return gson.fromJson(json, collectionType);
    }

    public static String generateRandomString(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }


}
