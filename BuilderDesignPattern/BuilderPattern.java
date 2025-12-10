import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// this patter ensures immutability and consistency

public class BuilderPattern {

    public static final class HTTPRequest {
        private final String method;
        private final String url;
        private final String body;
        private final int timeout;
        private final Map<String, String> headers;
        private final Map<String, String> queryParams;

        private HTTPRequest(HTTPRequestBuilder builder) {
            this.url = builder.url;
            this.method = builder.method;
            this.timeout = builder.timeout;
            this.headers = Collections.unmodifiableMap(new HashMap<>(builder.headers));
            this.queryParams = Collections.unmodifiableMap(new HashMap<>(builder.queryParams));
            this.body = builder.body;
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
    
    // Builder Class only responsible for building HTTPRequest object, we cant create HTTPRequest object directly
    public static class HTTPRequestBuilder {    // only responsible for building HTTPRequest object
        private String method = "GET";
        private String url;
        private String body = "";
        private int timeout = 30; // default
        private Map<String, String> headers = new HashMap<>();
        private Map<String, String> queryParams = new HashMap<>();
        // Method Chaining
        public HTTPRequestBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public HTTPRequestBuilder withMethod(String method) {
            this.method = method;
            return this;
        }

        public HTTPRequestBuilder withTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public HTTPRequestBuilder withHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public HTTPRequestBuilder withQueryParam(String key, String value) {
            this.queryParams.put(key, value);
            return this;
        }

        public HTTPRequestBuilder withBody(String body) {
            this.body = body;
            return this;
        }

        public HTTPRequest build() {                         // terminating method
            if( this.url == null || this.url.isEmpty()) {
                throw new IllegalStateException("URL cannot be null or empty");
            }
            return new HTTPRequest(this);
        }
    }
    public static void main(String[] args) {
        HTTPRequest request = new HTTPRequestBuilder()           // chain of method calls
                .withUrl("http://example.com/api/data")
                .withMethod("POST")
                .withTimeout(30)
                .withHeader("Content-Type", "application/json")
                .withQueryParam("version", "1.0")
                .withBody("{\"key\":\"value\"}")
                .build();
        request.execute();       // Gaurantee to be consistent state
    }
}
