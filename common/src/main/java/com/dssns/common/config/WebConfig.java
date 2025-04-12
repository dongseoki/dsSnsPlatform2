package com.dssns.common.config;

import com.dssns.common.interceptor.UserActivityInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

  private final ObjectMapper objectMapper = ModuleConfig.generateTimeSupportObjectMapper();

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    log.info("addInterceptors start");
    registry.addInterceptor(new UserActivityInterceptor(objectMapper)).addPathPatterns("/**")
        .excludePathPatterns("/css/**", "/images/**", "/js/**", "/swagger-ui/index.html");
  }

  @Bean
  public OncePerRequestFilter requestTimingFilter() {
    return new OncePerRequestFilter() {
      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
          FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);
        filterChain.doFilter(request, response);
      }
    };
  }
}
