package org.apache.dubbo.demo.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

import org.apache.dubbo.bootstrap.DubboBootstrap;
import org.apache.dubbo.demo.DemoService;

import java.util.concurrent.CountDownLatch;

public class ApiProviderApplication {

  public static void main(String[] args) throws Exception {
    //if (isClassic(args)) {
    startWithExport();
    //} else {
    //  startWithBootstrap();
    //}
  }

  private static boolean isClassic(String[] args) {
    return args.length > 0 && "classic".equalsIgnoreCase(args[0]);
  }

  private static void startWithBootstrap() {
    ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
    service.setInterface(DemoService.class);
    service.setRef(new DemoServiceImpl());
    service.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
    service.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

    new DubboBootstrap().registerServiceConfig(service).start();
  }

  private static void startWithExport() throws InterruptedException {
    ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
    service.setInterface(DemoService.class);
    service.setRef(new DemoServiceImpl());
    service.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
    service.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
    ProviderConfig providerConfig = new ProviderConfig();
    providerConfig.setServer("netty4");
    //providerConfig.setTransporter("netty4");
    service.setProvider(providerConfig);
    service.export();

    System.out.println("dubbo service started");
    new CountDownLatch(1).await();
  }
}
