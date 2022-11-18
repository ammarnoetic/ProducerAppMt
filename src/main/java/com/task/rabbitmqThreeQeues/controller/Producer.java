package com.task.rabbitmqThreeQeues.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Properties;


@RestController
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;

    @Autowired
    private RabbitAdmin admin;

    Logger log = LoggerFactory.getLogger(Producer.class.getName());


    @PostMapping(value = "/post/{id}")
    public String send(@PathVariable(value = "id") int message) {

        for (int i=message;i<=100;i++) {


            rabbitTemplate.convertAndSend(directExchange.getName(), "routing.B", message);//direct exhnage

        }
            return "message sent successsfully";
        }

        @PostMapping(value = "/send")
        public String sendMcg(String message){

        return message;
        }

        //for genertaion of alert if consumer is not running
    public static int checkmessage;
    @Scheduled(fixedRate = 2000)
    public void getCounts(){
        Properties props;
        Integer messageCount;
        //int store;
        //int checkmessage

        log.info("Fixed delay task -   Current Time "+ LocalDate.now());
            props = admin.getQueueProperties("lowMt");
            messageCount = Integer.parseInt(props.get("QUEUE_MESSAGE_COUNT").toString());

            System.out.println("lowMt"+ " has " + messageCount + " messages");


            //checkmessage=messageCount;
            //store=checkmessage;

        //System.out.println(checkmessage);
//
//            if (checkmessage<=messageCount){
//
//                checkmessage=messageCount;
//
//                System.out.println("if condition");
//
//               // System.out.println("send message to email");
//            }
//
//
//            else if (checkmessage>messageCount){
//
//                System.out.println(checkmessage);
//                System.out.println("mail generrated");
//            }


        if(checkmessage==0){
            checkmessage=messageCount;
            System.out.println("0 wali condition");
        }


         if (checkmessage<=messageCount){
            System.out.println(checkmessage);
            System.out.println("send message to email");
           // checkmessage=messageCount;
            System.out.println(checkmessage+"updated mcges");
        }

        checkmessage=messageCount;



    }







}
