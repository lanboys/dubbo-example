
package org.apache.dubbo.demo;

public interface DemoService {

  public String sayHello(String name);

  //default CompletableFuture<String> sayHelloAsync(String name) {
  //  return CompletableFuture.completedFuture(sayHello(name));
  //}
}