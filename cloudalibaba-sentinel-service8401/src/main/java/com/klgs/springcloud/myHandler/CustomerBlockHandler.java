package com.klgs.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.klgs.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException blockException){
        return new CommonResult(2020,"自定义的限流处理信息......CustomerBlockHandler");
    }
}
