package PaymentGatewayLLD.controllers;

import PaymentGatewayLLD.enums.GatewayType;
import PaymentGatewayLLD.factories.GatewayFactory;
import PaymentGatewayLLD.gateways.PaymentGateway;
import PaymentGatewayLLD.models.PaymentRequest;
import PaymentGatewayLLD.services.PaymentService;

public class PaymentController {
    private static final PaymentController instance = new PaymentController();

    private PaymentController() {}

    public static PaymentController getInstance() {
        return instance;
    }

    public boolean handlePayment(GatewayType type, PaymentRequest req) {
        PaymentGateway paymentGateway = GatewayFactory.getInstance().getGateway(type);
        PaymentService.getInstance().setGateway(paymentGateway);
        return PaymentService.getInstance().processPayment(req);
    }
}
