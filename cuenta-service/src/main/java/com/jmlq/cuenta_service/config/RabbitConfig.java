package com.jmlq.cuenta_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.exchange.cliente}")
    private String exchangeName;

    @Value("${rabbitmq.routingkey.cliente.created}")
    private String routingKey;

    @Value("${rabbitmq.queue.clienteCreated}")
    private String queueName;

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf) {
        RabbitTemplate tpl = new RabbitTemplate(cf);
        tpl.setMessageConverter(jackson2JsonMessageConverter());
        return tpl;
    }

    @Bean
    public DirectExchange clienteExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue clienteCreatedQueue() {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding bindingClienteCreated(Queue clienteCreatedQueue,
            DirectExchange clienteExchange) {
        return BindingBuilder.bind(clienteCreatedQueue)
                .to(clienteExchange)
                .with(routingKey);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory cf) {
        return new RabbitAdmin(cf);
    }

    /**
     * 1) Purga la cola de forma SÍNCRONA (noWait=false)
     * 2) Después arranca manualmente todos los listeners
     */
    @Bean
    public ApplicationRunner purgeThenStart(
            RabbitAdmin admin,
            RabbitListenerEndpointRegistry registry) {
        return args -> {
            admin.purgeQueue(queueName, false);
            System.out.println("Cola '" + queueName + "' purgada al arranque");
            registry.getListenerContainers().forEach(c -> c.start());
        };
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory cf,
            MessageConverter converter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cf);
        factory.setMessageConverter(converter);
        factory.setAutoStartup(false); // NO arranque automático
        factory.setDefaultRequeueRejected(false); // no recon encolado
        return factory;
    }
}