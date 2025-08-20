package com.kafka.payment_producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.io.Serializable;
import java.util.HashMap;

@Configuration
public class KafkaProducerConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaProducerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    /**+
     * Define as configurações para produzir a mensagem ao Kafka
     * @return ProducerFactory<String, Serializable>
     */
    @Bean
    public ProducerFactory<String, Serializable> producerFactory() {
        var configurations = new HashMap<String, Object>();

        // Obtém a informação definida no application.yml
        configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());

        // Define o serializador da chave da mensagem
        configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Define o serializador do valor da chave da mensagem
        configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configurations, new StringSerializer(), new JsonSerializer<>());
    }

    /**
     * Define o template para enviar a mensagem ao kafka
     * @param producerFactory, ProducerFactory<String, Serializable>
     * @return KafkaTemplate<String, Serializable>
     */
    @Bean
    public KafkaTemplate<String, Serializable> kafkaTemplate(ProducerFactory<String, Serializable> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
