package org.avaje.barftor.server.web;

import io.avaje.jex.Jex;
import io.avaje.jex.test.TestPair;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

class HelloTest {

  static TestPair pair = TestPair.create(Jex.create()
    .routing(routing -> routing
      .get("hello", ctx -> ctx.text("ok"))));

  @Test
  void hello() {
    final HttpResponse<String> health = pair.request().path("hello").GET().asString();
    assertThat(health.statusCode()).isEqualTo(200);
  }

  @AfterAll
  static void stop() {
    pair.shutdown();
  }
}
