import java.util.HashMap;
import java.util.Map;

public class BadCode {

    public static class HTTPRequest {
        private String method;
        private String url;
        private String body;
        private int timeout;
        private Map<String, String> headers=new HashMap<>();
        private Map<String, String> queryParams = new HashMap<>();

        
        public HTTPRequest(String url) {
            this.url = url;
            this.method = "GET";
            this.timeout = 30; // default timeout
        }

        public HTTPRequest(String url, String method) {
            this.url = url;
            this.method = method;
            this.timeout = 30; // default timeout
        }

        public HTTPRequest(String url, String method, int timeout) {
            this.url = url;
            this.method = method;
            this.timeout = timeout;
        }

        public HTTPRequest(String url, String method, int timeout, Map<String, String> headers) {
            this.url = url;
            this.method = method;
            this.timeout = timeout;
            this.headers = headers;
        }

        public HTTPRequest(String url, String method, int timeout, Map<String, String> headers,
                Map<String, String> queryParams) {
            this.url = url;
            this.method = method;
            this.timeout = timeout;
            this.headers = headers;
            this.queryParams = queryParams;
        }

        public HTTPRequest(String url, String method, int timeout, Map<String, String> headers,
                Map<String, String> queryParams, String body) {
            this.url = url;
            this.method = method;
            this.timeout = timeout;
            this.headers = headers;
            this.queryParams = queryParams;
            this.body = body;
        }

        // Setters leads to mutable object
        public void setUrl(String url) {
            this.url = url;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        public void addHeader(String key, String value) {
            this.headers.put(key, value);
        }

        public void addQueryParam(String key, String value) {
            this.queryParams.put(key, value);
        }

        public void execute() {
            System.out.println("Executing " + method + " request to " + url);
            if (!queryParams.isEmpty()) {
                System.out.print("Query parameters: ");
                for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
            }
            System.out.print("Headers: ");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

            if (!body.isEmpty()) {
                System.out.println("Body: " + body);
            }

            System.out.println("Timeout: " + timeout + " seconds");
            System.out.println("Request executed successfully!");

        }
    }

    public static void main(String[] args) {

        // Using setters to modify the request
        HTTPRequest request4 = new HTTPRequest("http://example.com/api");
        request4.setMethod("POST");
        request4.addHeader("Content-Type", "application/json");
        request4.addQueryParam("user", "123");
        request4.setBody("{\"name\":\"John Doe\"}");
        request4.setTimeout(45);

        request4.execute();
    }
}
