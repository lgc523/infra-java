package dev.spider.spi.api;

import dev.spider.spi.service.HelloService;

import java.util.ServiceLoader;

/**
 * @author spider
 */
public class SPIMain {
    public static void main(String[] args) {
        String a = Integer.toString(1) + Integer.toString(2) + Integer.toString(3);
        String b = "123";
        System.out.println(a.equals(b));
        System.out.println(a==b);
        System.out.println(a.intern()==b);

        ServiceLoader<HelloService> loaders = ServiceLoader.load(HelloService.class);
        if (loaders != null) {
            for (HelloService loader : loaders) {
                loader.sayHello();
            }
        }
    }
}
