package org.avaje.barftor.log4j;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AppenderTest {

  private static final Logger log = LoggerFactory.getLogger(AppenderTest.class);

  @Test
  void barf() {
    log.info("hello");
    BarftorRegister.registerWith("test", "local", "http://localhost:8905/api/ingest");
    try {
      failIt();
    } catch (Throwable e) {
      log.error("We have an error code:{}", "234234", e);
    }
  }

  private void failIt() {
    throw new IllegalStateException("Kind of expecting this");
  }
}
