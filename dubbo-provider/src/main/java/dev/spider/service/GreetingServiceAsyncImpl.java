package dev.spider.service;

import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author spider
 */
public class GreetingServiceAsyncImpl implements GreetingServiceAsync {
    private final int core = Runtime.getRuntime().availableProcessors();
    private final ThreadPoolExecutor bizThreadPool = new ThreadPoolExecutor(core, core * 2,
            5, TimeUnit.MICROSECONDS,
            new SynchronousQueue<>(), new NamedThreadFactory("biz-thread-pool"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public CompletableFuture<String> sayHello(String name) {
        //use threadPool
        RpcContext context = RpcContext.getContext();

        //run in ForkJoinPool#commonPool()
        /**
         CompletableFuture.supplyAsync(() -> {
         try {
         Thread.sleep(1000);
         } catch (Exception e) {

         }
         System.out.println("aysnc return");
         return "Hello " + name + " " + context.getAttachment("company");
         });
         */


        //dubbo 内部线程池释放
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            System.out.println("aysnc return");
            return "Hello " + name + " " + context.getAttachment("company");
        }, bizThreadPool);
    }
}
