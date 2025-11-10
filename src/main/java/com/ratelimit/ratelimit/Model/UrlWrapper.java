package com.ratelimit.ratelimit.Model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Wrapper class used for mapping a list of URL configurations
 * from an XML file. Each URL element represents a rate limiting rule.
 */
@JacksonXmlRootElement(localName = "urls")
public class UrlWrapper {

  /**
   * List of URL configurations extracted from XML.
   */
  @JacksonXmlProperty(localName = "url")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<Url> urls;

  /**
   * Returns the list of parsed URL configurations.
   *
   * @return list of Url objects.
   */
  public List<Url> getUrlList() {
    return urls;
  }

  /**
   * Sets the list of URL configurations.
   *
   * @param urls List of Url objects to assign.
   */
  public void setUrlList(List<Url> urls) {
    this.urls = urls;
  }
}
