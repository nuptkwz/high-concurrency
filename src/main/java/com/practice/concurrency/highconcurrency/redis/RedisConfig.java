package com.practice.concurrency.highconcurrency.redis;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;


/**
 * Description
 * Date 2020/7/6 23:40
 * Created by kwz
 */


@Configuration
public class RedisConfig {

    public JedisPool jedisPool(){

        return null;
    }
}
