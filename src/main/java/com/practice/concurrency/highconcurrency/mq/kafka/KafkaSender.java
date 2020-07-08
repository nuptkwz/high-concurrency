package com.practice.concurrency.highconcurrency.mq.kafka;

import com.practice.concurrency.highconcurrency.mq.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description
 * Date 2020/7/8 21:28
 * Created by kwz
 */
@Component
@Slf4j
public class KafkaSender {

//    private Kafka

    private void send(String msg) {
        Message message = Message.builder()
                .id(System.currentTimeMillis())
                .msg(msg)
                .sendTime(new Date())
                .build();
        log.info("send message:{}", message);

    }
}
