package com.klgs.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.klgs.springcloud.dao"})
public class MyBatisConfig {
}
