package com.vina.test.demo;

import com.vina.test.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fWang
 * @Description:
 * @date 2017/9/30 13:53
 */
@SpringBootTest(classes = UserService.class, properties = {"spring.profiles.active=dev"})
public class TestngSimpleTest extends AbstractTestNGSpringContextTests{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    AtomicInteger i = new AtomicInteger(0);

    @Test(invocationCount = 10,threadPoolSize = 5)
    public void updateProcess() throws Exception {

        System.out.println("test" + i);
        i.addAndGet(1);

    }

    @Test(dependsOnMethods = {"testSimplePrint1", "updateProcess"})
    public void testSimplePrint(){
        logger.info("simple print");
    }

    @Test
    public void testSimplePrint1() throws InterruptedException {
        logger.info("simple print1------start");
        //Thread.sleep(10000);
        logger.info("simple print1------end");
    }
}
