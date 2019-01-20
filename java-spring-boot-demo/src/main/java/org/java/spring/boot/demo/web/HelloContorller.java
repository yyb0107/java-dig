package org.java.spring.boot.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Controller
 @RequestMapping("/bingo")
@Slf4j
public class HelloContorller {
  @RequestMapping("hello")
  public String HelloWorld(Model model) {
    log.debug("run here....");
    model.addAttribute("name", "Hello");
    return "login";
  }
}
