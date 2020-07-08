package com.practice.concurrency.highconcurrency.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * Description
 * Date 2020/7/8 20:37
 * Created by kwz
 */
@Component
public class RedisClient {

    @Resource(name = "redisPool")
    private JedisPool jedisPool;

}
