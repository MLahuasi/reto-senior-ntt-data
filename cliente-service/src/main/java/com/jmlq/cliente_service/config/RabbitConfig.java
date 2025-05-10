package com.jmlq.cliente_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // ————————————————
    // 1) Conversor JSON
    // ————————————————
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // ————————————————
    // 2) RabbitTemplate
    // ————————————————
    // • MessageConverter para JSON
    // • Se añade replyTimeout
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf,
            MessageConverter jsonMessageConverter) {
        RabbitTemplate template = new RabbitTemplate(cf);
        // mantiene la serialización JSON de tu config original
        template.setMessageConverter(jsonMessageConverter);
        // NUEVO: retraso máximo en ms para recibir el reply en RPC
        template.setReplyTimeout(10_000);
        return template;
    }

    // ————————————————
    // 3) Exchange / Queue / Binding
    // ————————————————
    @Value("${rabbitmq.exchange.cliente}")
    private String exchangeName;

    @Value("${rabbitmq.routingkey.cliente.created}")
    private String routingKey;

    @Bean
    public DirectExchange clienteExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue clienteCreatedQueue() {
        return QueueBuilder.durable("cliente.created.queue").build();
    }

    @Bean
    public Binding bindingClienteCreated(Queue clienteCreatedQueue,
            DirectExchange clienteExchange) {
        return BindingBuilder
                .bind(clienteCreatedQueue)
                .to(clienteExchange)
                .with(routingKey);
    }
}
