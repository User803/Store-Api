package com.project.storeapi.services;

import org.springframework.stereotype.Service;

@Service("paypal")
public class PaypalPaymentService implements IPaymentService{

    @Override
    public void processPayment(double amount) {
        System.out.println("Paypal payment amount: " + amount);
    }
}
