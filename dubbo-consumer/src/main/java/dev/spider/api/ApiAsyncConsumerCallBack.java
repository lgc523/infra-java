package dev.spider.api;

import dev.spider.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.remoting.exchange.ResponseCallback;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.protocol.dubbo.FutureAdapter;

/**
 * @author spider
 */
public class ApiAsyncConsumerCallBack {
    public static void main(String[] args) throws InterruptedException {

        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("dubbo-consumer-async"));

        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(1100);
        referenceConfig.setVersion("0.0.1");
        referenceConfig.setGroup("dubbo");
        referenceConfig.setAsync(true);
        RpcContext.getContext().setAttachment("company", "laji");

        GreetingService greetingService = referenceConfig.get();
        System.out.println(greetingService.sayHello("async callback"));

        //future with callBack
        ((FutureAdapter) RpcContext.getContext().getFuture()).getFuture().setCallback(new ResponseCallback() {
            @Override
            public void done(Object  response) {
                System.out.println("result:" + response);
            }

            @Override
            public void caught(Throwable exception) {
                System.out.println("error:" + exception.getLocalizedMessage());
            }
        });
        Thread.currentThread().join();
    }
}
