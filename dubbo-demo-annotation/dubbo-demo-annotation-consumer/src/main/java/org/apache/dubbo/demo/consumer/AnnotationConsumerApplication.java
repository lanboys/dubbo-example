
package org.apache.dubbo.demo.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.demo.consumer.comp.DemoServiceComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class AnnotationConsumerApplication {

  /**
   * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true' before
   * launch the application
   */
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
    context.start();
    DemoService service = context.getBean("demoServiceComponent", DemoServiceComponent.class);
    String hello = service.sayHello("world");
    System.out.println("result :" + hello);
  }

  @Configuration
  @EnableDubbo(scanBasePackages = "org.apache.dubbo.demo.consumer.comp")
  @PropertySource("classpath:/spring/dubbo-consumer.properties")
  @ComponentScan(value = {"org.apache.dubbo.demo.consumer.comp"})
  static class ConsumerConfiguration {

  }
}
