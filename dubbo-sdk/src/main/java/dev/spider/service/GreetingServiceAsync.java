package dev.spider.service;

import java.util.concurrent.CompletableFuture;

/**
 * @author spider
 */
public interface GreetingServiceAsync {

    CompletableFuture<String> sayHello(String name);
}
