package ApiSteps;

import ApiData.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import http.HttpRequest;
import http.HttpRequestFactory;
import http.RequestType;

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
        for(int i = 0; i < 10; i++) {
            httpRequest.request(testData, "https", Const.baseUrl, Const.emailList(emailUserName), headers, null, null);
            System.out.println(Const.baseUrl + Const.emailList(emailUserName));
            if(testData.getStatusCode() <305) break;
            Thread.sleep(2000);
        }
        String responseBody = testData.getResponseBody();
        Gson gson = new Gson();
        Type founderListType = new TypeToken<ArrayList<ResponseBodyModel.EmailList>>(){}.getType();
        List<ResponseBodyModel.EmailList> emailList = gson.fromJson(responseBody, founderListType);
        testData.setEmailId(emailList.get(0).getEmailId());
        testData.setEmailSubject(emailList.get(0).getSubject());
        testData.setEmailStatusIsRead(emailList.get(0).isRead());
    }

}
