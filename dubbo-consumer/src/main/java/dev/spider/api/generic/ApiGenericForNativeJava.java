package dev.spider.api.generic;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.io.UnsafeByteArrayInputStream;
import org.apache.dubbo.common.io.UnsafeByteArrayOutputStream;
import org.apache.dubbo.common.serialize.Serialization;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author spider
 */
public class ApiGenericForNativeJava {
    public static void main(String[] args) throws Exception {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("consumer-native"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setVersion("0.0.1");
        referenceConfig.setGroup("dubbo");
        referenceConfig.setTimeout(1100);

        referenceConfig.setInterface("dev.spider.service.GreetingService");
        referenceConfig.setGeneric("nativejava");

        GenericService genericService = referenceConfig.get();

        UnsafeByteArrayOutputStream out = new UnsafeByteArrayOutputStream();
        ExtensionLoader.getExtensionLoader(Serialization.class)
                .getExtension(Constants.GENERIC_SERIALIZATION_NATIVE_JAVA).serialize(null, out).writeObject("world");

        Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"},
                new Object[]{out.toByteArray()});
        UnsafeByteArrayInputStream in = new UnsafeByteArrayInputStream((byte[]) result);
        System.out.println(ExtensionLoader.getExtensionLoader(Serialization.class).
                getExtension(Constants.GENERIC_SERIALIZATION_NATIVE_JAVA).deserialize(null, in).readObject());


    }
}
