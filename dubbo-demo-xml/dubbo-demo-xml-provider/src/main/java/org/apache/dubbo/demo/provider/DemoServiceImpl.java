
package org.apache.dubbo.demo.provider;

import com.alibaba.dubbo.rpc.RpcContext;

import org.apache.dubbo.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoServiceImpl implements DemoService {

  private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

  @Override
  public String sayHello(String name) {
    logger.info("Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
  }

  //@Override
  //public CompletableFuture<String> sayHelloAsync(String name) {
  //  CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
  //    //try {
  //    //  Thread.sleep(1000);
  //    //} catch (InterruptedException e) {
  //    //  e.printStackTrace();
  //    //}
  //    return "async result";
  //  });
  //  return cf;
  //}
}
