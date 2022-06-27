package com.klgs.springcloud.controller;

import com.klgs.springcloud.pojo.CommonResult;
import com.klgs.springcloud.pojo.Order;
import com.klgs.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult makeOrder(Order order){
        orderService.create(order);
        return new CommonResult(200, "订单完成");
    }
}
