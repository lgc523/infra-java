package dev.spider.spi.service.impl;

import dev.spider.spi.service.HelloService;

/**
 * @author spider
 */
public class ImageHello implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("image hello");
    }

}

