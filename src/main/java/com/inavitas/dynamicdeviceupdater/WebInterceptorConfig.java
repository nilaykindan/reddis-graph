package com.inavitas.dynamicdeviceupdater;

import com.inavitas.dynamicdeviceupdater.controller.advice.MDCLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ConditionalOnProperty(name = "mdc.enabled", havingValue = "true")
public class WebInterceptorConfig implements WebMvcConfigurer {
   @Autowired
   MDCLogInterceptor mdcLogInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(mdcLogInterceptor);
   }
}