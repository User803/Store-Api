package com.project.storeapi.manager;

import com.project.storeapi.services.IPaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentManager {

    private final IPaymentService paymentService;

    public PaymentManager(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processPayment(double amount) {
        paymentService.processPayment(amount);
    }
}
