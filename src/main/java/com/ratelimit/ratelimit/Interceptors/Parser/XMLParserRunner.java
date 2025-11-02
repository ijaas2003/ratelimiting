package com.ratelimit.ratelimit.Interceptors.Parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class XMLParserRunner implements CommandLineRunner {

  @Autowired
  private XMLParser xmlParser;

  @Override
  public void run(String... args) throws Exception {
    xmlParser.parse();
  }
}
