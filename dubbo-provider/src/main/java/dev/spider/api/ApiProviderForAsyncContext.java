package dev.spider.api;

import dev.spider.service.GreetingServiceAsyncContextImpl;
import dev.spider.service.GreetingServiceRpcContext;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * async service provider
 * @author spider
 */
public class ApiProviderForAsyncContext {
    public static void main(String[] args) throws IOException {
        ServiceConfig<GreetingServiceRpcContext> asyncServiceConfig = new ServiceConfig<>();
        asyncServiceConfig.setApplication(new ApplicationConfig("async-provider"));
        asyncServiceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        asyncServiceConfig.setInterface(GreetingServiceRpcContext.class);
        asyncServiceConfig.setRef(new GreetingServiceAsyncContextImpl());
        asyncServiceConfig.setVersion("0.0.1");
        asyncServiceConfig.setGroup("dubbo");

        asyncServiceConfig.setAsync(true);

        asyncServiceConfig.export();
        System.out.println("async service provider start");
        System.in.read();
    }
}
