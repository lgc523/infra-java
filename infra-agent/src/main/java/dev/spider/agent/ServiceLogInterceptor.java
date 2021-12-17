package dev.spider.agent;

import java.util.List;
import java.util.concurrent.Callable;

public class ServiceLogInterceptor {
    private Log log = LogFactory.getLog(ServiceLogInterceptor.class);


    private ServiceLogHelperFactory serviceLogHelperFactory;

    public ServiceLogInterceptor(ServiceLogHelperFactory serviceLogHelperFactory) {
        this.serviceLogHelperFactory = serviceLogHelperFactory;
    }

    @RuntimeType
    public Object intercept(@AllArguments Object[] args, @Origin Method method, @SuperCall Callable<?> callable) {
        long start = System.currentTimeMillis();
        long costTime = 0L;
        String status = ServiceLog.SUCEESS;
        Object result = null;
        String respResult = null;
        try {
            // 原有函数执行
            result = callable.call();
            respResult = JsonUtils.object2json(result);
        } catch (Exception e){
            log.error(e.getMessage(),e);
            status = ServiceLog.FAIL;
            respResult = e.getMessage();
        } finally{
            costTime = System.currentTimeMillis() - start;
            saveLog(args, method, costTime, status, respResult);
        }
        return result;
    }

    private void saveLog(Object[] args, Method method, long costTime, String status, String respResult) {
        if(!isSkipLog(method)){
            ServiceLog serviceLog = serviceLogHelperFactory.createServiceLog(args,method);
            serviceLog.setCostTime(costTime);
            serviceLog.setRespResult(respResult);
            serviceLog.setStatus(status);
            ServiceLogHelper serviceLogHelper = serviceLogHelperFactory.getServiceLogHelper();
            serviceLogHelper.saveLog(serviceLog);
        }
    }


    private boolean isSkipLog(Method method){
        ServiceLogProperties serviceLogProperties = serviceLogHelperFactory.getServiceLogProperties();
        List<String> skipLogServiceNameList = serviceLogProperties.getSkipLogServiceNameList();
        if(!CollectionUtils.isEmpty(skipLogServiceNameList)){
            String currentServiceName = method.getDeclaringClass().getName() + ServiceLogProperties.CLASS_METHOD_SPITE + method.getName();
            return skipLogServiceNameList.contains(currentServiceName);
        }
        return false;
    }



}

