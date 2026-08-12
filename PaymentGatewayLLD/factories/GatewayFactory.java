package PaymentGatewayLLD.factories;

import PaymentGatewayLLD.enums.GatewayType;
import PaymentGatewayLLD.gateways.PaymentGateway;
import PaymentGatewayLLD.gateways.PaytmGateway;
import PaymentGatewayLLD.gateways.RazorpayGateway;
import PaymentGatewayLLD.proxies.PaymentGatewayProxy;

public class GatewayFactory {
     private static final GatewayFactory instance = new GatewayFactory();

    private GatewayFactory() {}

    public static GatewayFactory getInstance() {
        return instance;
    }

    public PaymentGateway getGateway(GatewayType type) {
        if (type == GatewayType.PAYTM) {
            PaymentGateway paymentGateway = new PaytmGateway();
            return new PaymentGatewayProxy(paymentGateway, 3);
        } else {
            PaymentGateway paymentGateway = new RazorpayGateway();
            return new PaymentGatewayProxy(paymentGateway, 1);
        }
    }
}
