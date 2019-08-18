package com.mmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableRedisHttpSession
@SpringBootApplication
@MapperScan(basePackages = "com.mmall.dao")
public class MyMmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMmallApplication.class, args);
	}

}
