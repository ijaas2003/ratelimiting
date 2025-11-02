package com.ratelimit.ratelimit.Model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "urls")
public class UrlWrapper {

  @JacksonXmlProperty(localName = "urls")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<Url> urls;

  public List<Url> getUrlList() {

    return urls;
  }

  public void setUrlList(List<Url> urls) {
    this.urls = urls;
  }
}
