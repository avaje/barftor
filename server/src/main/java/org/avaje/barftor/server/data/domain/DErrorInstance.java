package org.avaje.barftor.server.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "error_instance")
public class DErrorInstance extends BaseModel {

  @ManyToOne(optional = false)
  private final DErrorStack error;
  @Column(length = 30, nullable = false)
  private final String app;
  @Column(length = 30, nullable = false)
  private final String env;
  @Column(length = 400, nullable = false)
  private final String line;

  public DErrorInstance(String line, DErrorStack error, String app, String env) {
    this.line = line;
    this.error = error;
    this.app = app;
    this.env = env;
  }

  public DErrorStack getError() {
    return error;
  }

  public String getApp() {
    return app;
  }

  public String getEnv() {
    return env;
  }

  public String getLine() {
    return line;
  }
}
