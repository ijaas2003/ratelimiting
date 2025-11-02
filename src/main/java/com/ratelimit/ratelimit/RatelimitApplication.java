package com.ratelimit.ratelimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ratelimit.ratelimit.Interceptors.Parser.XMLParser;

@SpringBootApplication
public class RatelimitApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(RatelimitApplication.class, args);
    new XMLParser().parse();
  }

}
