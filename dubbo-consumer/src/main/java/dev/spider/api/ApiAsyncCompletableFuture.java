package dev.spider.api;

import dev.spider.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;

/**
 * @author spider
 */
public class ApiAsyncCompletableFuture {
    public static void main(String[] args) throws InterruptedException {
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("dubbo-async-completeAble-future"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(2000);
        referenceConfig.setVersion("0.0.1");
        referenceConfig.setGroup("dubbo");

        RpcContext.getContext().setAttachment("company", "laji");
        referenceConfig.setAsync(true);
        GreetingService greetingService = referenceConfig.get();
        System.out.println(greetingService.sayHello("艹"));

        //complete future
        CompletableFuture<Object> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println(v);
            }
        });
        System.out.println("over");
        Thread.currentThread().join();
    }
}
