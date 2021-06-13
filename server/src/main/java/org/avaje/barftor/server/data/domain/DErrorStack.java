package org.avaje.barftor.server.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "error_stack")
public class DErrorStack extends BaseModel {

  @Column(length = 50, nullable = false)
  private final String hash;

  @Column(length = 400, nullable = false)
  private final String matchLine;

  @Lob
  private final String stackLines;

  public DErrorStack(String hash, String matchLine, String stackLines) {
    this.hash = hash;
    this.matchLine = matchLine;
    this.stackLines = stackLines;
  }

  public String getHash() {
    return hash;
  }

  public String getMatchLine() {
    return matchLine;
  }

  public String getStackLines() {
    return stackLines;
  }
}
