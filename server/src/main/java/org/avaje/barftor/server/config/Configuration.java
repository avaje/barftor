package org.avaje.barftor.server.config;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.PreDestroy;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.event.ShutdownManager;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Factory
class Configuration  {

  private static final Logger log = LoggerFactory.getLogger(Configuration.class);

  @Bean
  public Database database() {
    ShutdownManager.deregisterShutdownHook();

    // final String name = Config.get("datasource.db.username");
    DatabaseConfig config = new DatabaseConfig();
    config.loadFromProperties();
    return DatabaseFactory.create(config);
  }

  @PreDestroy
  public void shutdown() {
    log.info("shutting down ...");
    ShutdownManager.shutdown();
    LogManager.shutdown();
    log.info("stopped");
  }

}
