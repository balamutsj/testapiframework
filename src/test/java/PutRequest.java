import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PutRequest implements HttpRequest {
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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpPut httpPut = new HttpPut(uri.toString());

        try {
            Iterator<Map.Entry<String, String>> itr = headers.entrySet().iterator();
            while(itr.hasNext())
            {
                Map.Entry<String, String> entry = itr.next();
                httpPut.setHeader(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(testData.getToken()!=null){
            httpPut.setHeader("jwt-auth", testData.getToken());
        }
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert stringEntity != null;
        httpPut.setEntity(stringEntity);

        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert response != null;

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
