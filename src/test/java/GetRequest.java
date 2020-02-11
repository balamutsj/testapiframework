import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GetRequest implements HttpRequest {
    public ApiTestData request(ApiTestData testData, String scheme, String baseUrl, String path, Map<String, String> headers, Map<String, String> params, String body) {
        URIBuilder uri = new URIBuilder().setScheme(scheme)
                .setHost(baseUrl)
                .setPath(path);
        try {
        Iterator<Map.Entry<String, String>> itr = params.entrySet().iterator();
        while(itr.hasNext())
        {
            Map.Entry<String, String> entry = itr.next();
            uri.addParameter(entry.getKey(), entry.getValue());
        }

            uri.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpGet httpGet = new HttpGet(uri.toString());

        try {
            Iterator<Map.Entry<String, String>> itr = headers.entrySet().iterator();
            while(itr.hasNext())
            {
                Map.Entry<String, String> entry = itr.next();
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(testData.getToken()!=null){
            httpGet.setHeader("jwt-auth", testData.getToken());
        }

        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Header[] responseAllHeaders = response.getAllHeaders();
        HashMap responseHeaders = new HashMap();
        for (Header header : responseAllHeaders) {
            responseHeaders.put(header.getName(), header.getValue());
        }
        testData.setResponseHeaders(responseHeaders);

        HttpEntity result = response.getEntity();
        String responseBody = null;
        try {
            responseBody = EntityUtils.toString(result);
        } catch (IOException e) {
            e.printStackTrace();
        }


        testData.setStatusCode(response.getStatusLine().getStatusCode());
        testData.setResponseBody(responseBody);
        return testData;
    }
}
