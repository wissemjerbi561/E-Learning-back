package com.paiement.Controllers;

import com.paiement.Entities.Payment;
import com.paiement.Repositories.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import com.stripe.model.checkout.Session;

import org.springframework.beans.factory.annotation.Value;


import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/payment")
public class StripeController {

    @Autowired
    private PaymentRepository paymentRepository;
    @Value("${stripe.key.secret}")
    private String stripeApiKey;

    private Payment currentPayment;
    // create a Gson object
    private static Gson gson = new Gson();

    @PostMapping("/stripe")
    public String createPaymentSession(@RequestBody Payment payment) throws StripeException {
        Stripe.apiKey = stripeApiKey;
        CustomerCreateParams params =
                CustomerCreateParams.builder()
                        .build();

        Customer customer = Customer.create(params);

        SessionCreateParams.Builder paramsBuilder = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.PAYPAL)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.LINK)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(payment.getCancelUrl())
                .setCustomer(customer.getId())
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


        SessionCreateParams paramstripe = paramsBuilder.build();
        // create a stripe session
        Session session = Session.create(paramstripe);
        Map<String, String> responseData = new HashMap<>();
        // We get the sessionId and we put it inside the response data. You can get more info from the session object.
        responseData.put("id", session.getId());
        System.out.println(responseData);



        payment.setAmount(payment.getAmount());
        payment.setCurrency(payment.getCurrency());
        payment.setCustomerId(customer.getId());
        payment.setMemberId(payment.getMemberId());
        payment.setFirstName(payment.getFirstName());
        payment.setLastName(payment.getLastName());
        currentPayment = payment;
        return gson.toJson(responseData);
    }
    @PostMapping("/sucess")
    public ResponseEntity<String> Sucess(){
        if (currentPayment != null) {
            // Enregistrer le paiement actuel dans la base de données
            paymentRepository.save(currentPayment);

            // Réinitialiser le paiement actuel
            currentPayment = null;

            return ResponseEntity.status(HttpStatus.OK).body("Payment saved to the database.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("No current payment to save.");
        }

    }


}

