package com.wtz.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description：
 * Date:2021/11/14 20:18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class LogTest {
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void test1() {
        System.out.println(log);
        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");
        new Thread(()->{
            log.info(Thread.currentThread().getName());
        },"t1").start();
    }

}
