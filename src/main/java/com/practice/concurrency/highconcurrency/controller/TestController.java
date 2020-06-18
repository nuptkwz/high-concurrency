package com.practice.concurrency.highconcurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 * Date 2020/6/18 0:56
 * Created by kwz
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
