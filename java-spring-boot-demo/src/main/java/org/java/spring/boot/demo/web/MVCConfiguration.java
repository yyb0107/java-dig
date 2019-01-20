package org.java.spring.boot.demo.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class MVCConfiguration implements WebMvcConfigurer {
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/");
    viewResolver.setSuffix(".html");
    return viewResolver;
  }
//TODO if I comment this method,the spring.mvc.static-path-pattern: /kkk/WEB-INF/** will be not work
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }
  
//  public void addResourceHandlers(ResourceHandlerRegistry registry){
//       registry
//         .addResourceHandler("/WEB-INF/*")
//          .addResourceLocations("/WEB-INF/")
//          .setCachePeriod(0);
//  }
}
