package com.hyz.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.hyz.mall.mbg.mapper")
public class MyBatisConfig {
}
