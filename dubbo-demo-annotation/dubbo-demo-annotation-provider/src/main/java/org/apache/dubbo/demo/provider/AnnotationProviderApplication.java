
package org.apache.dubbo.demo.provider;

import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class AnnotationProviderApplication {

  public static void main(String[] args) throws Exception {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
    context.start();
    System.in.read();
  }

  @Configuration
  @EnableDubbo(scanBasePackages = "org.apache.dubbo.demo.provider")
  @PropertySource("classpath:/spring/dubbo-provider.properties")
  static class ProviderConfiguration {

    @Bean
    public RegistryConfig registryConfig() {
      RegistryConfig registryConfig = new RegistryConfig();
      registryConfig.setAddress("zookeeper://127.0.0.1:2181");
      return registryConfig;
    }
  }
}
