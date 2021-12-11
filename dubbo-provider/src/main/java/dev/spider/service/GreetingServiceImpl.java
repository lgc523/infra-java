package dev.spider.service;

import dev.spider.entity.PoJO;
import dev.spider.entity.Result;
import org.apache.dubbo.common.json.JSON;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author spider
 */
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        return "hello " + name + " " + RpcContext.getContext().getAttachment("company");
    }

    @Override
    public Result<String> testGeneric(PoJO poJO) {
        Result<String> result = new Result<>();
        result.setSuccess(true);
        try {
            result.setData(JSON.json(poJO));
        } catch (Exception e) {

        }
        return result;
    }
}
