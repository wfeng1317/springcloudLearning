package com.klgs.springcloud.service;

import com.klgs.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult payment(Long id) {
        return new CommonResult(44444, "PaymentFallbackService 服务降级出现错误");
    }
}
