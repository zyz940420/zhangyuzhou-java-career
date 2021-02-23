package com.yuzhou.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("basic")
public class BasicController {
    private static volatile AtomicInteger count = new AtomicInteger(0);

    @Retryable
    @RequestMapping("testDemo")
    public String testDemo() throws Exception {
        count.getAndAdd(1);
        System.err.println(count);

        long times = System.currentTimeMillis();
        log.info("hello times:{}", times);
        if (times % 4 != 100000) {
            log.warn("发生异常，time：{}", LocalTime.now());
            throw new Exception("发生Hello异常");
        }
        return "hello " + this.getClass().getSimpleName();
    }
}
