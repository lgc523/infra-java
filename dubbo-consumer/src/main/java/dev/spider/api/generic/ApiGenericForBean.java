package dev.spider.api.generic;

import org.apache.dubbo.common.beanutil.JavaBeanDescriptor;
import org.apache.dubbo.common.beanutil.JavaBeanSerializeUtil;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author spider
 */
public class ApiGenericForBean {
    public static void main(String[] args) {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("generic-consumer-bean"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        referenceConfig.setVersion("0.0.1");
        referenceConfig.setGroup("dubbo");

        referenceConfig.setTimeout(1100);

        //generic
        referenceConfig.setInterface("dev.spider.service.GreetingService");
        referenceConfig.setGeneric("bean");

        GenericService genericService = referenceConfig.get();

        RpcContext.getContext().setAttachment("company", "bean");
        JavaBeanDescriptor param = JavaBeanSerializeUtil.serialize("world");
        Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{param});

        result = JavaBeanSerializeUtil.deserialize((JavaBeanDescriptor) result);
        System.out.println(result);

    }
}
