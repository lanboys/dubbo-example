
package org.apache.dubbo.demo;

import java.util.concurrent.CompletableFuture;

public interface DemoService {

  String sayHello(String name);

  default CompletableFuture<String> sayHelloAsync(String name) {
    return CompletableFuture.completedFuture(sayHello(name));
  }
}