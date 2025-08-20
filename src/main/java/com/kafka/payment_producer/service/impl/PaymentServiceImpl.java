package com.kafka.payment_producer.service.impl;

import com.kafka.payment_producer.dto.request.PaymentRequest;
import com.kafka.payment_producer.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    public PaymentServiceImpl(KafkaTemplate<String, Serializable> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPayment(PaymentRequest paymentRequest) {
        try {
            logger.info("[PAYMENT PRODUCER] Pagamento do cliente: {} recebido com sucesso!", paymentRequest.clientId());
            Thread.sleep(1000);
            logger.info("[PAYMENT PRODUCER] Enviando pagamento do cliente: {} para o Kafka!", paymentRequest.clientId());
            kafkaTemplate.send("payment-topic", paymentRequest)
                    .whenComplete((sucess, error) -> {
                        if (error != null) {
                            logger.error("[PAYMENT PRODUCER] Erro ao realizar envio do pagamento do cliente: {} para o kafka", paymentRequest.clientId(), error);
                        } else {
                            logger.info("[PAYMENT PRODUCER] Pagamento do cliente: {} enviado com sucesso! - Partition: {} - Offset: {}", paymentRequest.clientId(),
                                                sucess.getRecordMetadata().partition(), sucess.getRecordMetadata().offset());
                        }
                    });
        } catch (Exception e) {
            logger.error("[PAYMENT PRODUCER] Erro ao enviar o pagamento do cliente: {}! - ERRO: {}", paymentRequest.clientId(), e.getMessage());
        }
    }

}
