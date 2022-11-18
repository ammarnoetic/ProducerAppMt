package com.task.rabbitmqThreeQeues;

import com.task.rabbitmqThreeQeues.controller.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@EnableScheduling
@SpringBootApplication
public class RabbitmqThreeQeuesApplication {

	@Autowired
	private Producer producer;

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqThreeQeuesApplication.class, args);
	}

	@PostConstruct
	public void call(){
		producer.getCounts();

	}

}
