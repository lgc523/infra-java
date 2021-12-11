package dev.spider.api;

import dev.spider.service.GreetingServiceRpcContext;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author spider
 */
public class ApiConsumerForAsyncContextProvider {
    public static void main(String[] args) {
        ReferenceConfig<GreetingServiceRpcContext> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("async-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingServiceRpcContext.class);
        referenceConfig.setTimeout(1100);
        referenceConfig.setVersion("0.0.1");
        referenceConfig.setGroup("dubbo");

        GreetingServiceRpcContext greetingServiceRpcContext = referenceConfig.get();
        RpcContext.getContext().setAttachment("company", "艹");
        RpcContext.getContext().set("ip", "1.1");

        String laji = greetingServiceRpcContext.sayHello("laji");
        System.out.println(laji);
    }
}
