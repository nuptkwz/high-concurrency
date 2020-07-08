package com.practice.concurrency.highconcurrency.mq;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Description
 * Date 2020/7/8 21:31
 * Created by kwz
 */
@Data
@Builder
public class Message {

    private Long id;
    private String msg;
    private Date sendTime;
}
