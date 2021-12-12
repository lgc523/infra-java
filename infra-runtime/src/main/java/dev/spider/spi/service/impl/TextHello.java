package dev.spider.spi.service.impl;

import dev.spider.spi.service.HelloService;

/**
 * @author spider
 */
public class TextHello implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("text hello");
    }
}
