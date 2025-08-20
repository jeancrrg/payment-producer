package com.kafka.payment_producer.dto.request;

import java.io.Serializable;

/**
 * @author Jean Garcia
 * @param clientId
 * @param productId
 * @param cardNumber
 */
public record PaymentRequest(Long clientId, Long productId, String cardNumber) implements Serializable {

}
