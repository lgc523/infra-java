package dev.spider.service;

import dev.spider.entity.PoJo;
import dev.spider.entity.Result;

/**
 * @author spider
 */
public class GreetingServiceMock implements GreetingService {
    @Override
    public String sayHello(String name) {
        return "MOCK VALUE";
    }

    @Override
    public Result<String> testGeneric(PoJo poJO) {
        return null;
    }
}
