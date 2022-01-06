
package org.apache.dubbo.demo.consumer.comp;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.demo.DemoService;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component("demoServiceComponent")
public class DemoServiceComponent implements DemoService {

  @Reference
  private DemoService demoService;

  @Override
  public String sayHello(String name) {
    return demoService.sayHello(name);
  }

  @Override
  public CompletableFuture<String> sayHelloAsync(String name) {
    return null;
  }
}
