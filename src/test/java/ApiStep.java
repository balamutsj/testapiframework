import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiStep {

    public static void ahemSystemCheck(ApiTestData testData) {
        HttpRequestFactory requestFactory = new HttpRequestFactory();
        HttpRequest httpRequest = requestFactory.sendRequest(RequestType.GET);
        httpRequest.request(testData, "https", Const.baseUrl, Const.systemCheck, ApiHeader.defaultHeader(), null, null);
    }

    public static void getAhemServiceEmailToken(ApiTestData testData) {
        HttpRequestFactory requestFactory = new HttpRequestFactory();
        HttpRequest httpRequest = requestFactory.sendRequest(RequestType.POST);
        String body = ApiBody.createRequestBodyAuthToken();
        httpRequest.request(testData, "https", Const.baseUrl, Const.tokenPath, ApiHeader.defaultHeader(), null, body);
        String responseBody = testData.getResponseBody();
        ResponseBodyModel.TokenData tokenData = (ResponseBodyModel.TokenData) ApiUtils.getPropertyFromResponseBody(ResponseBodyModel.TokenData.class, responseBody);
        testData.setToken(tokenData.getToken());
    }

    public static void getAhemEmailList(ApiTestData testData, String emailUserName) throws InterruptedException {
        HttpRequestFactory requestFactory = new HttpRequestFactory();
        HttpRequest httpRequest = requestFactory.sendRequest(RequestType.GET);
        Map<String, String> headers = ApiHeader.defaultHeader();
        headers.put("Authorization", "Bearer " + testData.getToken());
        /*headers.put("Some-token", "7edb7c92-d3cc-446f-a2c5-33f970736e4b");
        headers.put("cache-control", "no-cache");
        headers.put("User-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.87 Safari/537.36 ");
        headers.put("Host", "www.ahem.email");
        headers.put("accept-encoding", "gzip, deflate");
        headers.put("cookie", "__cfduid=de9b347b426e3ea6e542a06490c5318ce1581343278");*/
        for(int i = 0; i < 10; i++) {
            httpRequest.request(testData, "https", Const.baseUrl, Const.emailList(emailUserName), headers, null, null);
            System.out.println(Const.baseUrl + Const.emailList(emailUserName));
            if(testData.getStatusCode() <305) break;
            Thread.sleep(2000);
        }
        System.out.println(Const.baseUrl + Const.emailList(emailUserName) +
                headers);
        String responseBody = testData.getResponseBody();
        Gson gson = new Gson();
        Type founderListType = new TypeToken<ArrayList<ResponseBodyModel.EmailList>>(){}.getType();
        List<ResponseBodyModel.EmailList> emailList = gson.fromJson(responseBody, founderListType);
    }

}
