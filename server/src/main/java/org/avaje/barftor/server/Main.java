package org.avaje.barftor.server;

import io.avaje.config.Config;
import io.avaje.inject.BeanScope;
import io.avaje.jex.Jex;
import io.avaje.jex.Routing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

    final int port = Config.getInt("server.http.port", 8905);

    final BeanScope beanScope = BeanScope.newBuilder().withShutdownHook(true).build();
    final Jex.Server server =
      new Main()
        .run(port, beanScope);

    log.info("App started");
  }

  public Jex.Server run(int port, BeanScope beanScope) {
    return Jex.create()
      .port(port)
      .routing(beanScope.list(Routing.Service.class))
      .start();
  }
}
