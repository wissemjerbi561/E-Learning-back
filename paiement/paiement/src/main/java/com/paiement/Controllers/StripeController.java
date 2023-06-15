package com.paiement.Controllers;

import com.paiement.Entities.Payment;
import com.paiement.Repositories.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

import com.stripe.model.checkout.Session;

import org.springframework.beans.factory.annotation.Value;


import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/payment")
public class StripeController {

    @Autowired
    private PaymentRepository paymentRepository;
    @Value("${stripe.key.secret}")
    private String stripeApiKey;
    // create a Gson object
    private static Gson gson = new Gson();

    @PostMapping("/stripe")
    public String createPaymentSession(@RequestBody Payment payment) throws StripeException {
        Stripe.apiKey = stripeApiKey;
        SessionCreateParams.Builder paramsBuilder = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(payment.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency())
                                                .setUnitAmount(payment.getAmount())
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(payment.getName())
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                );

        // Set the customer email
        SessionCreateParams params = paramsBuilder.build();
        // create a stripe session
        Session session = Session.create(params);
        String clientEmail = session.getCustomerEmail();
        Map<String, String> responseData = new HashMap<>();
        // We get the sessionId and we put it inside the response data. You can get more info from the session object.
        responseData.put("id", session.getId());

        Payment newPayment = new Payment();
        newPayment.setAmount(payment.getAmount());
        newPayment.setCurrency(payment.getCurrency());
        newPayment.setEmail(session.getCustomerEmail());
        paymentRepository.save(newPayment);

        return gson.toJson(responseData);
    }

    private static void init() {
        Stripe.apiKey = "sk_test_51NGo80Dc30MIimI9frAhY178qMZtMN9fMNKIosUZC815bDaucMf2bDiM6GI2SltvxRNfL6u1MhSJvzeR1NnjjWOG0040w6Zzy9";
    }
}
