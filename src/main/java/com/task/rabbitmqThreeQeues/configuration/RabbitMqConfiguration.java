package com.task.rabbitmqThreeQeues.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfiguration {

    //direct exchange
    @Bean
    Queue queueA(){
        return new Queue("highMt",false);
    }

    @Bean
    Queue queueB(){
        return new Queue("lowMt",false);
    }

    @Bean
    DirectExchange exchange(){
        return new DirectExchange("exchange.direct");
    }
    @Bean
    Binding binding(Queue queueA,DirectExchange exchange){

        return BindingBuilder.bind(queueA)
                .to(exchange)
                .with("routing.A");
    }

    @Bean
    Binding bindingB(Queue queueB,DirectExchange exchange){

        return BindingBuilder.bind(queueB)
                .to(exchange)
                .with("routing.B");
    }




    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }



    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){

        RabbitTemplate rabbitTemplate= new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
