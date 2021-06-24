package com.example.cicdtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class CiCdTemplateApplication {

  /**
   * spring-boot main class.
   */
  public static void main(String[] args) {
    SpringApplication.run(CiCdTemplateApplication.class, args);
  }

  // TODO fix this later!
  /**
   *sample rest API for demonstration.
  */
  @GetMapping(value = "/ping")
  public String ping() {
    return "pong";
  }

}
