
package org.apache.dubbo.demo.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.demo.DemoService;

import java.util.concurrent.CountDownLatch;

public class ApiProviderApplication {

  public static void main(String[] args) throws Exception {
    if (isClassic(args)) {
      startWithExport();
    } else {
      startWithBootstrap();
    }
  }

  private static boolean isClassic(String[] args) {
    return args.length > 0 && "classic".equalsIgnoreCase(args[0]);
  }

  private static void startWithBootstrap() {
    ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
    service.setInterface(DemoService.class);
    service.setRef(new DemoServiceImpl());

    DubboBootstrap bootstrap = DubboBootstrap.getInstance();
    bootstrap.application(new ApplicationConfig("dubbo-demo-api-provider"))
        .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
        .service(service)
        .start()
        .await();
  }

  private static void startWithExport() throws InterruptedException {
    ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
    service.setInterface(DemoService.class);
    service.setRef(new DemoServiceImpl());
    service.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
    service.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
    service.export();

    System.out.println("dubbo service started");
    new CountDownLatch(1).await();
  }
}
