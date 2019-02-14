package org.java.spring.boot.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@EnableWebMvc
public class App extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
  
  

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    // TODO Auto-generated method stub
    return builder.sources(App.class);
  }



  @Bean
  public CommandLineRunner command() {
    return (sth) -> {
      System.out.println("Hello World");
    };
  }
}
