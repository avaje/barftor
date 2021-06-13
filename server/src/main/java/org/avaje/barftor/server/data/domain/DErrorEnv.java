package org.avaje.barftor.server.data.domain;

import io.ebean.annotation.Length;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "error_env")
public class DErrorEnv extends BaseModel {

  @ManyToOne(optional = false)
  private final DErrorStack error;
  @Length(30)
  private final String app;
  @Length(30)
  private final String env;
  @Length(400)
  private final String line;

  public DErrorEnv(DErrorStack error, String app, String env, String line) {
    this.error = error;
    this.app = app;
    this.env = env;
    this.line = line;
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
