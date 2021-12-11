package dev.spider.service;

import dev.spider.entity.PoJO;
import dev.spider.entity.Result;

/**
 * @author spider
 */
public interface GreetingService {
    String sayHello(String name);

    Result<String> testGeneric(PoJO poJO);
}
