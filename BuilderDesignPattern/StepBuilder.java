// We build object step by step ensuring that all mandatory fields are set before object creation

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StepBuilder {

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

    // Interfaces to ensure steps
    public interface URLStep {
        public MethodStep withUrl(String url);
    }

    public interface MethodStep {
        public HeaderSteps withMethod(String method);
    }

    public interface HeaderSteps {
        public OptionalSteps withHeader(String key, String value);
    }

    public interface OptionalSteps {
        public OptionalSteps withTimeout(int timeout);

        public OptionalSteps withBody(String body);

        public HTTPRequest build();
    }

    public static class HTTPRequestBuilder implements URLStep, MethodStep, HeaderSteps, OptionalSteps {
        private String method;
        private String url;
        private String body = "";
        private int timeout = 30; // default
        private Map<String, String> headers = new HashMap<>();
        private Map<String, String> queryParams = new HashMap<>();

        @Override
        public MethodStep withUrl(String url) {
            this.url = url;
            return this;
        }

        @Override
        public HeaderSteps withMethod(String method) {
            this.method = method;
            return this;
        }

        @Override
        public OptionalSteps withHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        @Override
        public OptionalSteps withTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        @Override
        public OptionalSteps withBody(String body) {
            this.body = body;
            return this;
        }

        @Override
        public HTTPRequest build() { // terminating method
            if (this.url == null || this.url.isEmpty()) {
                throw new IllegalStateException("URL cannot be null or empty");
            }
            if (this.method == null || this.method.isEmpty()) {
                throw new IllegalStateException("Method cannot be null or empty");
            }
            return new HTTPRequest(this);
        }
        
        public static URLStep getBuilder() {
            return new HTTPRequestBuilder();
        }
    }

    public static void main(String[] args) {
        HTTPRequest stepRequest=HTTPRequestBuilder.getBuilder()
            .withUrl("https://api.example.com/products")
            .withMethod("POST")
            .withHeader("{Content-Type}","application/json")
            .withBody("{\"product\":\"laptop\",\"price\":49999}")
            .withTimeout(45)
            .build();
        stepRequest.execute();
    }
}
