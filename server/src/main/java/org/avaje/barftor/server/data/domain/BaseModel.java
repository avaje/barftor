package org.avaje.barftor.server.data.domain;

import io.ebean.annotation.WhenCreated;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseModel {

  @Id
  protected long id;

  @WhenCreated
  protected Instant whenCreated;

  @Version
  protected long version;

  public long getId() {
    return id;
  }

  public Instant getWhenCreated() {
    return whenCreated;
  }

  public long getVersion() {
    return version;
  }
}
