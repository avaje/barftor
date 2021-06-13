package org.avaje.barftor.server.web;

import javax.validation.constraints.Size;

public class IngestForm {

  @Size(max = 30)
  public String app;

  @Size(max = 30)
  public String env;

  public String message;

  public String stackTrace;

  @Override
  public String toString() {
    return "IngestForm{" +
      "app='" + app + '\'' +
      ", env='" + env + '\'' +
      ", message='" + message + '\'' +
      ", stackTrace='" + stackTrace + '\'' +
      '}';
  }
}
