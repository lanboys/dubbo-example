package org.apache.dubbo.demo.consumer;

import org.apache.dubbo.demo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;

public class XmlConsumerApplication {

  /**
   * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true' before
   * launch the application
   */
  public static void main(String[] args) throws Exception {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
    context.start();
    DemoService demoService = context.getBean("demoService", DemoService.class);
    CompletableFuture<String> hello = demoService.sayHelloAsync("world");
    System.out.println("result: " + hello.get());
  }
}
