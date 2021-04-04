package com.springbootcacheable.springbootcacheable.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private CorrelationInterceptor correlationInterceptor;

    @Autowired
    public InterceptorConfig(CorrelationInterceptor correlationInterceptor) {
        this.correlationInterceptor = correlationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(correlationInterceptor);
    }
}
