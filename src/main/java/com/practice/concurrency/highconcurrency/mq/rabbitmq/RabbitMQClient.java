package com.practice.concurrency.highconcurrency.mq.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description
 * Date 2020/7/10 23:47
 * Created by kwz
 */
@Component
public class RabbitMQClient {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend(QueueConstants.TEST, message);
    }
}
