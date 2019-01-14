package org.java.spring.boot.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/hello")
@Slf4j
public class HelloContorller {
  @RequestMapping("/test1")
  @ResponseBody
  public String HelloWorld() {
    log.debug("run here....");
    return "Hello wrold!";
  }
}
