package org.avaje.barftor.server.web;


import org.avaje.barftor.server.data.domain.DErrorInstance;
import org.avaje.barftor.server.data.domain.DErrorStack;
import org.avaje.barftor.server.data.domain.query.QDErrorInstance;
import org.avaje.barftor.server.data.domain.query.QDErrorStack;
import io.avaje.http.client.HttpClientContext;
import io.avaje.inject.BeanScope;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

class IngestControllerComponentTest extends BaseWebTest {

  static HttpClientContext client = init(BeanScope.newBuilder());

  @Test
  void one() {
    client.request().path("health").GET().asVoid();
  }

  @Test
  void ingest() {

    client.request().path("health").GET().asVoid();

    final HttpResponse<Void> res = client.request().path("api/ingest")
      .formParam("app", "myApp")
      .formParam("env", "localTest")
      .formParam("stackTrace", "simpleStack 1234\norg.foo.One.m1()\norg.foo.Two.m2()")
      .POST()
      .asVoid();

    assertThat(res.statusCode()).isEqualTo(201);

    // assert db contains error_stack row
    final DErrorStack err = new QDErrorStack()
      .matchLine.eq("simpleStack <n>")
      .findOne();

    assertThat(err.getMatchLine()).isEqualTo("simpleStack <n>");
    assertThat(err.getHash()).isEqualTo("6fad9c42523ff773279f72ede762ce30");
    assertThat(err.getStackLines()).isEqualTo("org.foo.One.m1()\norg.foo.Two.m2()");

    final DErrorInstance errorInstance = new QDErrorInstance()
      .error.eq(err)
      .line.contains("simpleStack 1234")
      .findOne();

    assertThat(errorInstance.getLine()).isEqualTo("simpleStack 1234");

  }
}
