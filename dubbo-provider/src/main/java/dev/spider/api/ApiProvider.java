package dev.spider.api;

import dev.spider.service.GreetingService;
import dev.spider.service.GreetingServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * simple service provider
 * @author spider
 */
public class ApiProvider {
    public static void main(String[] args) throws IOException {
        //1.instance
        ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<>();
        //2.cnf
        serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));
        //3.reg center
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");
        serviceConfig.setRegistry(registryConfig);
        //4.inter
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setRef(new GreetingServiceImpl());
        //5.set group version
        serviceConfig.setGroup("dubbo");
        serviceConfig.setVersion("0.0.1");
        //6.threadPool
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("threadpool", "myThreadPool");
//        serviceConfig.setParameters(parameters);
        //7.export
        serviceConfig.export();
        //8.block
        System.out.println("service is started");
        System.in.read();
    }
}
