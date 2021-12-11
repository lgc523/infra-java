package dev.spider.service;

import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author spider
 */
public class GreetingServiceAsyncContextImpl implements GreetingServiceRpcContext {

    private final int core = Runtime.getRuntime().availableProcessors();
    private final ThreadPoolExecutor bizThreadPool = new ThreadPoolExecutor(core, core * 2, 5, TimeUnit.SECONDS,
            new SynchronousQueue<>(), new NamedThreadFactory("biz-thread-pool"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public String sayHello(String name) {
        final AsyncContext asyncContext = RpcContext.startAsync();
        bizThreadPool.execute(() -> {
            //use context
            asyncContext.signalContextSwitch();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            asyncContext.write("hello " + name + " " + RpcContext.getContext().getAttachment("company"));
        });
        //write
        return null;
    }
}
