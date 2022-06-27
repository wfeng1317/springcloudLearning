package com.klgs.springcloud.controller;

import com.klgs.springcloud.entities.CommonResult;
import com.klgs.springcloud.entities.Payment;
import com.klgs.springcloud.lb.MyLoadBalanced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

@RestController
public class OrderController {
    public static final String PAYMENT_ADDRESS = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private MyLoadBalanced myLoadBalanced;
    @Resource
    private DiscoveryClient discoveryClient;



    @GetMapping("/consumer/payment/create")
    public CommonResult<Integer> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_ADDRESS + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_ADDRESS + "/payment/getById/" + id, CommonResult.class);
    }
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getForEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_ADDRESS + "/payment/getById/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<Payment>(444, "查询出错");
        }
    }


    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        ServiceInstance instance = myLoadBalanced.getInstance(discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE"));
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }


    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }




}
