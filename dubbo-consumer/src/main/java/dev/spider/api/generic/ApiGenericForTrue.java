package dev.spider.api.generic;

import org.apache.dubbo.common.json.JSON;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author spider
 */
public class ApiGenericForTrue {
    public static void main(String[] args) throws IOException {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("generic-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setVersion("0.0.1");
        referenceConfig.setGroup("dubbo");

        referenceConfig.setTimeout(1100);

        //generic ref
        referenceConfig.setInterface("dev.spider.service.GreetingService");
        referenceConfig.setGeneric(true);

        GenericService genericService = referenceConfig.get();
        RpcContext.getContext().setAttachment("company","generic");
        Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"generic"});
        System.out.println(JSON.json(result));

        HashMap<String, Object> map = new HashMap<>();
        map.put("class", "dev.spider.entity.PoJo");
        map.put("id", "1997");
        map.put("name", "spider");

        result = genericService.$invoke("testGeneric", new String[]{"dev.spider.entity.PoJo"}, new Object[]{map});
        System.out.println(result);


    }
}
