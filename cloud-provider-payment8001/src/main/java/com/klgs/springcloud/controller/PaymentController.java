package com.klgs.springcloud.controller;

import com.klgs.springcloud.entities.CommonResult;
import com.klgs.springcloud.entities.Payment;
import com.klgs.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String SERVER_PORT;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        if(i > 0){
            return new CommonResult<>(200, "插入成功, SERVER_PORT" + SERVER_PORT, i);
        }else{
            return new CommonResult<>(444, "插入失败, SERVER_PORT" + SERVER_PORT, null);
        }
    }
    @GetMapping("/payment/getById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        int t = 0;
        if(payment != null){
            return new CommonResult<>(200, "查询成功, SERVER_PORT" + SERVER_PORT, payment);
        }else{
            return new CommonResult<>(444, "查询id: " + id + "失败SERVER_PORT" + SERVER_PORT, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("服务名：{}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return SERVER_PORT;
    }

    @GetMapping(value = "/payment/timeout")
    public void getPaymentTimeout() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
