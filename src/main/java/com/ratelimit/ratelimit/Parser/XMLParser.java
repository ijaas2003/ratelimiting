package com.ratelimit.ratelimit.Parser;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ratelimit.ratelimit.Model.UrlWrapper;

/**
 * @author Ijaas Ahamad
 * 
 *
 *
 * @apiNote This class is very helpfull to
 *          {@link ch.qos.logback.core.pattern.parser.Parser} the xml and to
 *          store the data in {@value Redis}
 *
 **/

@Component
public class XMLParser {
  private static final String xmlFilePath = "/Users/ijaas-22254/learn/project/ratelimit/src/main/resources/api.xml";

  /**
   * This is method whicj we are using to parse the
   * {@link com.ratelimit.ratelimit.Parser.XMLParser}
   **/

  @Autowired
  public XMLParserUtil xmlParserUtil;

  public void parse() throws Exception {
    XmlMapper xmlMapper = new XmlMapper();
    UrlWrapper urlWrapper = xmlMapper.readValue(new File(xmlFilePath), UrlWrapper.class);
    boolean isAdded = xmlParserUtil.storeURLInStore(urlWrapper);
    if (isAdded) {
      System.out.println("Data Being populated");
    } else {
      System.out.println("Data Not Being populated");
    }
  }
}
