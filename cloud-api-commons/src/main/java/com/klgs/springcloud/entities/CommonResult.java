package com.klgs.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T result;

    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}
