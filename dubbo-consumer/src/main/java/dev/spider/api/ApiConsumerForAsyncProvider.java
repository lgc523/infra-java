package dev.spider.api;

import dev.spider.service.GreetingServiceAsync;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;

/**
 * @author spider
 */
public class ApiConsumerForAsyncProvider {
    public static void main(String[] args) throws InterruptedException {
        ReferenceConfig<GreetingServiceAsync> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("async consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingServiceAsync.class);
        referenceConfig.setTimeout(1100);
        referenceConfig.setVersion("0.0.1");
        referenceConfig.setGroup("dubbo");

        GreetingServiceAsync greetingServiceAsync = referenceConfig.get();
        RpcContext.getContext().setAttachment("company", "艹");
        RpcContext.getContext().set("ip", "1.1");

        CompletableFuture<String> future = greetingServiceAsync.sayHello("laji");
        //reg callBack
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println(v);
            }
        });
        Thread.currentThread().join();
    }
}
