package com.springbootcacheable.springbootcacheable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootCacheableApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheableApplication.class, args);
	}

}
