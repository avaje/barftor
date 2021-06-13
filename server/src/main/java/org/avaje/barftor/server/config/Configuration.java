package org.avaje.barftor.server.config;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.ebean.DB;
import io.ebean.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Factory
class Configuration implements AutoCloseable {

  private static final Logger log = LoggerFactory.getLogger(Configuration.class);

  @Bean
  public Database database() {
    // final String name = Config.get("datasource.db.username");
    return DB.getDefault();
  }

  @Override
  public void close() {
    log.info("closing");
  }

}
