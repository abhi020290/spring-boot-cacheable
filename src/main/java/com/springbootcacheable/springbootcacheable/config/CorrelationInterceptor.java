package com.springbootcacheable.springbootcacheable.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
@Slf4j
public class CorrelationInterceptor implements HandlerInterceptor {

    private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
    private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) throws Exception {
        final String correlationId = getCorrelationIdFromHeader(request);
        MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex) throws Exception {
        MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ServletOutputStream servletOutputStream = response.getOutputStream();
        String responseStr=new String(String.valueOf(servletOutputStream));
        log.info("Response status: {} , response body: {} request url: {} ", response.getStatus(),responseStr , request.getRequestURI());
    }

    private String getCorrelationIdFromHeader(final HttpServletRequest request) {
        String correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
        if (StringUtils.isBlank(correlationId)) {
            correlationId = generateUniqueCorrelationId();
        }
        return correlationId;
    }

    private String generateUniqueCorrelationId() {
        return UUID.randomUUID().toString();
    }
}