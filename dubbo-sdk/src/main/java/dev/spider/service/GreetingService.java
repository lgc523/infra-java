package dev.spider.service;

import dev.spider.entity.PoJo;
import dev.spider.entity.Result;

/**
 * @author spider
 */
public interface GreetingService {
    String sayHello(String name);

    Result<String> testGeneric(PoJo poJO);
}
