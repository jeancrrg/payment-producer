package com.kafka.payment_producer.service;

import com.kafka.payment_producer.dto.request.PaymentRequest;

public interface PaymentService {

    void sendPayment(PaymentRequest paymentRequest);

}
