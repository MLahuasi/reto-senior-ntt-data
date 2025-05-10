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

    @Value("${rabbitmq.exchange.cliente}")
    private String exchangeName;

    @Value("${rabbitmq.routingkey.cliente.created}")
    private String routingKey;

    // 1) Definimos un único conversor JSON
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 2) Creamos el RabbitTemplate y lo marcamos como @Primary o único
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf,
            MessageConverter jsonMessageConverter) {
        RabbitTemplate template = new RabbitTemplate(cf);
        template.setMessageConverter(jsonMessageConverter);
        return template;
    }

    // 3) Exchange / Queue / Binding (idéntico a antes)
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
