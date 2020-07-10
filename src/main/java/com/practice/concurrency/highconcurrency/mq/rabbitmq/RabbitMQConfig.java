package com.practice.concurrency.highconcurrency.mq.rabbitmq;

/**
 * Description
 * Date 2020/7/10 23:37
 * Created by kwz
 */

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue(QueueConstants.TEST);
    }
}
