package com.example.cicdtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class CiCdTemplateApplication {

  //TODO more tests needed
  /**
   * spring-boot main class.
   * @param args - Standard main args
   */
  public static void main(String[] args) {
    SpringApplication.run(CiCdTemplateApplication.class, args);
  }

  // TODO fix this later!
  /**
   *sample rest API for demonstration.
   *
   *@return pong response
  */
  @GetMapping(value = "/ping")
  public String ping() {

    String s = null;
    if(false){
      s.toString();
    }
   
    return "pong";
  }

}
