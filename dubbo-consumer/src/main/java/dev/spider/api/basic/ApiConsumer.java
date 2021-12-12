package dev.spider.api.basic;

import dev.spider.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author spider
 */
public class ApiConsumer {
    public static void main(String[] args) {
        //1.service ref instance
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        //2.app info
        referenceConfig.setApplication(new ApplicationConfig("consumer"));
        //3.reg center
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        //4.inter timeout
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(3000);
        //5.lb
//        referenceConfig.setLoadbalance("myLoadBalance");
//        referenceConfig.setCluster("myBroadcast");
        //6.group version
        referenceConfig.setVersion("0.0.1");
        referenceConfig.setGroup("dubbo");
        //7.ref
        GreetingService greetingService = referenceConfig.get();
        RpcContext.getContext().setAttachment("company", "laji");
        System.out.println(greetingService.sayHello("company"));

    }
}
