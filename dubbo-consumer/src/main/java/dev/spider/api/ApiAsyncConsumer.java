package dev.spider.api;

import dev.spider.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author spider
 */
public class ApiAsyncConsumer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-async-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setVersion("0.0.1");
        referenceConfig.setGroup("dubbo");
        //default timeout 1000
        referenceConfig.setTimeout(1100);

        referenceConfig.setAsync(true);
        GreetingService greetingService = referenceConfig.get();
        System.out.println(greetingService.sayHello("async laji"));

        Future<Object> future = RpcContext.getContext().getFuture();
        System.out.println(future.get());
    }
}
