package dev.spider.api.mock;

import dev.spider.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author spider
 */
public class ApiConsumerMock {
    public static void main(String[] args) {
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("consumer-mock"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setVersion("0.0.1");
        referenceConfig.setGroup("dubbo");
        referenceConfig.setTimeout(1100);


        referenceConfig.setCheck(false);
        referenceConfig.setMock("true");

        GreetingService greetingService = referenceConfig.get();
        RpcContext.getContext().setAttachment("company","spider");
        System.out.println(greetingService.sayHello("spider"));
    }
}
