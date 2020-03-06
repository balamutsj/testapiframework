package ApiSteps;

import ApiData.*;
import http.HttpRequest;
import http.HttpRequestFactory;
import http.RequestType;

import java.util.List;
import java.util.Map;

public class ApiStep {

    private static HttpRequest sendGet() {
        HttpRequestFactory requestFactory = new HttpRequestFactory();
        return requestFactory.sendRequest(RequestType.GET);
    }

    private static HttpRequest sendPost() {
        HttpRequestFactory requestFactory = new HttpRequestFactory();
        return requestFactory.sendRequest(RequestType.POST);
    }

    public static void ahemSystemCheck(ApiTestData testData) {
        sendGet().request(testData, "https", Const.baseUrl, Const.systemCheck, ApiHeader.defaultHeader(), null, null);
    }

    public static void getAhemServiceEmailToken(ApiTestData testData) {
        String body = ApiBody.createRequestBodyAuthToken();
        sendPost().request(testData, "https", Const.baseUrl, Const.tokenPath, ApiHeader.defaultHeader(), null, body);
        String responseBody = testData.getResponseBody();
        ResponseBodyModel.TokenData tokenData = (ResponseBodyModel.TokenData) ApiUtils.fromJsonObject(ResponseBodyModel.TokenData.class, responseBody);
        testData.setToken(tokenData.getToken());
    }

    public static void getAhemEmailList(ApiTestData testData, String emailUserName) throws InterruptedException {
        Map<String, String> headers = ApiHeader.defaultHeader();
        headers.put("Authorization", "Bearer " + testData.getToken());
        for(int i = 0; i < 10; i++) {
            sendGet().request(testData, "https", Const.baseUrl, Const.emailList(emailUserName), headers, null, null);
            System.out.println(Const.baseUrl + Const.emailList(emailUserName));
            if(testData.getStatusCode() <305) break;
            Thread.sleep(2000);
        }
        List <ResponseBodyModel.EmailList> emailList = ApiUtils.fromJsonArrayList(testData.getResponseBody(), ResponseBodyModel.EmailList.class);
        testData.setEmailId(emailList.get(0).getEmailId());
        testData.setEmailSubject(emailList.get(0).getSubject());
        testData.setEmailStatusIsRead(emailList.get(0).isRead());
    }

}
