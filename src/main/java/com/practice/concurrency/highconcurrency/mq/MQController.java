package com.practice.concurrency.highconcurrency.mq;

import com.practice.concurrency.highconcurrency.mq.kafka.KafkaSender;
import com.practice.concurrency.highconcurrency.mq.rabbitmq.RabbitMQClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description
 * Date 2020/7/10 23:51
 * Created by kwz
 */
@RestController
@RequestMapping("mq")
public class MQController {

    @Resource
    private RabbitMQClient rabbitMQClient;

    @Resource
    private KafkaSender kafkaSender;

    @PostMapping("/rabbit-send")
    public String send(@RequestParam("message") String message) {
        rabbitMQClient.send(message);
        return "success";
    }

    @PostMapping("/kafka-send")
    public String kafkaSend(@RequestParam("message") String message) {
        kafkaSender.send(message);
        return "success";
    }
}
