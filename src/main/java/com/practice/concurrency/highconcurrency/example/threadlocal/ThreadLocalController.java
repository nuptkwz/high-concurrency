package com.practice.concurrency.highconcurrency.example.threadlocal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 * Date 2020/6/21 17:53
 * Created by kwz
 */
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @GetMapping("/test")
    public Long test() {
        return RequestHolder.getId();
    }
}
