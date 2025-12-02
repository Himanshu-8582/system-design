public class AdapterPattern {
    
    public interface Report {                                                // Target
        public String getJsonData(String data);
    }

    public static class XmlDataProvider {                                    // Adaptee
        public String getXmlData(String data) {
            int sep = data.indexOf(':');
            String name = data.substring(0, sep);
            String id = data.substring(sep + 1);
            return "<user>"
                    + "<name>" + name + "</name>"
                    + "<id>" + id + "</id>"
                    + "</user>";
        }
    }


    public static class XmlDataProviderAdapter implements Report {            // Adapter , Helps to communicate between Target and Adaptee
        private XmlDataProvider xmlDataProvider;

        public XmlDataProviderAdapter(XmlDataProvider xmlDataProvider) {
            this.xmlDataProvider = xmlDataProvider;
        }

        @Override
        public String getJsonData(String data) {
            String xmlData = xmlDataProvider.getXmlData(data);
            String name = xmlData.split("<name>")[1].split("</name>")[0];
            String id = xmlData.split("<id>")[1].split("</id>")[0];
            return "{ \"name\": \"" + name + "\", \"id\": \"" + id + "\" }";
        }
    }


    public static class Client {                                                // Client
        public void getReport(Report report, String data) {
            System.out.println("Processing Json Report...");
            String jsonData = report.getJsonData(data);
            System.out.println("Generated Report: " + jsonData);
        }
    }


    public static void main(String[] args) {
        XmlDataProvider xmlDataProvider = new XmlDataProvider();
        Report adapter = new XmlDataProviderAdapter(xmlDataProvider);

        String rawData = "Alice:42";
        Client client = new Client();
        client.getReport(adapter, rawData);

    }
}
