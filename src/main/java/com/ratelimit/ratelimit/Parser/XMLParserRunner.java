package com.ratelimit.ratelimit.Parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Runner class that triggers XML parsing when the Spring Boot application
 * starts.
 * <p>
 * Implements {@link CommandLineRunner} to automatically execute the parsing
 * logic after the application context is fully initialized.
 */
@Component
public class XMLParserRunner implements CommandLineRunner {

  @Autowired
  private XMLParser xmlParser;

  /*
   * Executes on application startup and invokes the XML parser
   * to load and process the initial XML configuration.
   *
   * @param args command-line arguments passed during application startup
   * 
   * @throws Exception if an error occurs while parsing XML
   */
  @Override
  public void run(String... args) throws Exception {
    xmlParser.parse();
  }
}
