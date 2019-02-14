package org.java.spring.boot.demo.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfiguration implements WebMvcConfigurer {
//  @Bean
//  public ViewResolver viewResolver() {
//    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//    viewResolver.setViewClass(JstlView.class);
//    viewResolver.setPrefix("/WEB-INF/");
//    viewResolver.setSuffix(".html");
//    return viewResolver;
//  }
  
  /**
   * Add DefaultServletHttpRequestHandler 
   */
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
