package org.java.spring.boot.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/hello")
@Slf4j
public class HelloContorller {
  @GetMapping("/test1")
//  @ResponseBody
  public String  HelloWorld(Model model) {
    log.debug("run here....");
    model.addAttribute("name", "Hello");
    return "login";
  }
}
