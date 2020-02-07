

import java.util.Map;

public class PostRequest2 implements HttpRequest {
    public ApiTestData request(ApiTestData testData, String scheme, String baseUrl, String path, Map<String, String> headers, Map<String, String> params, String body) {
        /*URIBuilder uri = new URIBuilder().setScheme(scheme)
                .setHost(baseUrl);

        try {
            uri.setPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        HttpPost httpPost = new HttpPost(uri.toString());

        httpPost.setHeader("Accept", "application/json, text/plain");//TODO change cont-type
        httpPost.setHeader("Content-type", "application/json");

        final FileBody file = new FileBody(new File(Const.filesFolderPath + "/testOne.jpg"));
        final StringBody json = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

        final HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("bin", file)
                .addPart("comment", json)
                .build();


        httpPost.setEntity(reqEntity);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert response != null;

        return null;*/
        return null;
    }
}
