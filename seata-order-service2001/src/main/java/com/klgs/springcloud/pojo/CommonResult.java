package com.klgs.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResult {
    private int code;
    private String message;
}
