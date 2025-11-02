package com.ratelimit.ratelimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ratelimit.ratelimit")
public class RatelimitApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(RatelimitApplication.class, args);
  }
}
