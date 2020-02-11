package ApiData;

import java.util.HashMap;
import java.util.Map;

public class ApiHeader {

    public static Map<String, String> defaultHeader(){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "*/*");
        //headers.put("Content-type", "application/json");
        return headers;
    }
}
