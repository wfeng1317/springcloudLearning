package com.klgs.springcloud.controller;

import com.klgs.springcloud.entities.CommonResult;
import com.klgs.springcloud.entities.Payment;
import com.klgs.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/getById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/timeout")
    public void getPaymentTimeout() {
        paymentService.getPaymentTimeout();
    }
}
