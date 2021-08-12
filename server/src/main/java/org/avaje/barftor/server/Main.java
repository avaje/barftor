package org.avaje.barftor.server;

import io.avaje.config.Config;
import io.avaje.inject.BeanScope;
import io.avaje.jex.Jex;
import io.ebean.event.ShutdownManager;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    log.info("starting at jvm:{}ms", jvmUptime());
    final long start = System.currentTimeMillis();
    final int port = Config.getInt("server.http.port", 8905);

    final BeanScope beanScope = BeanScope.newBuilder().build();

    ShutdownManager.deregisterShutdownHook();

    new Main().run(port, beanScope)
      .onShutdown(() -> {
        ShutdownManager.shutdown();
        LogManager.shutdown();
      });
    log.info("App started {}ms jvm:{}ms", (System.currentTimeMillis() - start), jvmUptime());
  }

  public Jex.Server run(int port, BeanScope beanScope) {
    return Jex.create()
      .configureWith(beanScope)
      .port(port)
      .start();
  }

  private static long jvmUptime() {
    try {
      return ManagementFactory.getRuntimeMXBean().getUptime();
    } catch (Throwable e) {
      return 0;
    }
  }
}
