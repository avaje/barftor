package org.avaje.barftor.server;

import io.avaje.config.Config;
import io.avaje.inject.BeanScope;
import io.avaje.jex.Jex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

    final int port = Config.getInt("server.http.port", 8905);

    final BeanScope beanScope = BeanScope.newBuilder().build();

//    ShutdownManager.deregisterShutdownHook();
//    log.info("shutting down ...");
//    ShutdownManager.shutdown();
//    LogManager.shutdown();
//    log.info("stopped");

    new Main().run(port, beanScope);
    log.info("App started");
  }

  public Jex.Server run(int port, BeanScope beanScope) {
    return Jex.create()
      .configureWith(beanScope)
      .port(port)
      .start();
  }
}
