package com.klgs.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MyLoadBalanced {
    ServiceInstance getInstance(List<ServiceInstance> serviceInstances);
}
