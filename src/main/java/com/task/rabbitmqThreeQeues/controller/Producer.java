package com.task.rabbitmqThreeQeues.controller;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;


    @PostMapping(value = "/post/{id}")
    public String send(@PathVariable(value = "id") int message) {

        for (int i=message;i<=100;i++) {


            rabbitTemplate.convertAndSend(directExchange.getName(), "routing.B", message);//direct exhnage

        }
            return "message sent successsfully";
        }





}
