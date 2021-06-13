package org.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BarfIntegrationTest {

  private static final Logger log = LoggerFactory.getLogger(BarfIntegrationTest.class);

  @Test
  void expectBarf() {
    System.setProperty("app.name", "test-me");
    try {
      willFail();
    } catch (Throwable e) {
      log.error("My message {}", "12345", e);
    }
  }

  private void willFail() {
    throw new RuntimeException("we fail");
  }
}
