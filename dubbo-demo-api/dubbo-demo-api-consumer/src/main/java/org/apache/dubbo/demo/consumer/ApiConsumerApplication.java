package org.apache.dubbo.demo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;

import org.apache.dubbo.demo.DemoService;

public class ApiConsumerApplication {

  public static void main(String[] args) {
    //if (isClassic(args)) {
      runWithRefer();
    //} else {
    //  runWithBootstrap();
    //}
  }

  private static boolean isClassic(String[] args) {
    return args.length > 0 && "classic".equalsIgnoreCase(args[0]);
  }

  private static void runWithBootstrap() {
    ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
    reference.setInterface(DemoService.class);
    reference.setGeneric("true");

    //DubboBootstrap bootstrap = DubboBootstrap.getInstance();
    //bootstrap.application(new ApplicationConfig("dubbo-demo-api-consumer"))
    //    .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
    //    .reference(reference)
    //    .start();

    DemoService demoService = ReferenceConfigCache.getCache().get(reference);
    String message = demoService.sayHello("dubbo");
    System.out.println(message);

    // generic invoke
    GenericService genericService = (GenericService) demoService;
    Object genericInvokeResult = genericService.$invoke("sayHello", new String[]{String.class.getName()},
        new Object[]{"dubbo generic invoke"});
    System.out.println(genericInvokeResult);
  }

  private static void runWithRefer() {
    ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
    reference.setApplication(new ApplicationConfig("dubbo-demo-api-consumer"));
    reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
    reference.setInterface(DemoService.class);
    //reference.setClient("netty4");
    ConsumerConfig consumer = new ConsumerConfig();
    consumer.setClient("netty4");
    reference.setConsumer(consumer);
    DemoService service = reference.get();
    String message = service.sayHello("dubbo");
    System.out.println(message);
  }
}
