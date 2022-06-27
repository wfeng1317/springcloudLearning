package com.klgs.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalanced01 implements MyLoadBalanced{

    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public ServiceInstance getInstance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }

    public final int getAndIncrement(){
        int cur;
        int next;
        do{
            cur = atomicInteger.get();
            next = cur == Integer.MAX_VALUE ? 0 : cur + 1;
        }while(!atomicInteger.compareAndSet(cur, next));
        System.out.println("第几次请求" + next);
        return next;
    }
}
