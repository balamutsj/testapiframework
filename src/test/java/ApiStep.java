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



}
