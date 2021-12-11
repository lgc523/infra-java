package dev.spider.api;

import dev.spider.service.GreetingServiceAsync;
import dev.spider.service.GreetingServiceAsyncImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * async service provider
 * @author spider
 */
public class ApiProviderForAsync {
    public static void main(String[] args) throws IOException {
        ServiceConfig<GreetingServiceAsync> asyncServiceConfig = new ServiceConfig<>();
        asyncServiceConfig.setApplication(new ApplicationConfig("async provider"));
        asyncServiceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        asyncServiceConfig.setInterface(GreetingServiceAsyncImpl.class);
        asyncServiceConfig.setRef(new GreetingServiceAsyncImpl());
        asyncServiceConfig.setVersion("0.0.1");
        asyncServiceConfig.setGroup("dubbo");

        asyncServiceConfig.export();
        System.out.println("async service provider start");
        System.in.read();
    }
}
