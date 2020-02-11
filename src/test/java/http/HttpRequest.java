package http;

import ApiData.ApiTestData;

import java.util.Map;

public interface HttpRequest {
    ApiTestData request(ApiTestData testData, String scheme, String baseUrl, String path, Map<String, String> headers, Map<String, String> params, String body);
}
