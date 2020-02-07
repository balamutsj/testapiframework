public class HttpRequestFactory {
    public HttpRequest sendRequest(RequestType requestType){
        HttpRequest httpRequest = null;
        switch (requestType) {
            case POST:
                httpRequest = new PostRequest();
                break;
            case GET:
                httpRequest = new GetRequest();
                break;
            case PUT:
                break;
            case DELETE:
                break;
            default:
                throw new IllegalArgumentException("Wrong request type:" + requestType);
        }
        return httpRequest;
    }
}
