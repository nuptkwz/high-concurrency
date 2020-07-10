package com.practice.concurrency.highconcurrency.mq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description
 * Date 2020/7/10 23:49
 * Created by kwz
 */
@Slf4j
@Component
public class RabbitMQServer {

    @RabbitListener(queues = QueueConstants.TEST)
    private void receive(String message) {
        log.info("{}", message);
    }
}
