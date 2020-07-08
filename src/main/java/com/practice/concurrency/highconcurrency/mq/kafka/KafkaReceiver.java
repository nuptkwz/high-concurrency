package com.practice.concurrency.highconcurrency.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;

/**
 * Description
 * Date 2020/7/8 21:28
 * Created by kwz
 */
@Component
@Slf4j
public class KafkaReceiver {


    @KafkaListener(topics = {TopicConstants.TEST})
    public void receive(ConsumerRecord<?, ?> record) {
        log.info("record:{}", record);
    }
}
