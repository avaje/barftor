package org.avaje.barftor.server.web;

import io.avaje.http.client.HttpClientContext;
import io.avaje.http.client.JacksonBodyAdapter;
import io.avaje.inject.BeanScope;
import io.avaje.jex.Jex;
import org.avaje.barftor.server.Main;
import org.junit.jupiter.api.AfterAll;

import java.util.Random;

public class BaseWebTest {

  public static String baseUrl;
  public static Jex.Server webServer;
  public static BeanScope beanScope;

  public static HttpClientContext init(BeanScope scope) {
    int port = 11000 + new Random().nextInt(1000);
    beanScope = scope;
    baseUrl = "http://localhost:" + port;
    webServer = new Main().run(port, scope);
    return createWebClient();
  }

  @AfterAll
  public static void shutdown() {
    webServer.shutdown();
    beanScope.close();
  }

  public static HttpClientContext createWebClient() {
    return HttpClientContext.newBuilder()
      .baseUrl(baseUrl)
      .bodyAdapter(new JacksonBodyAdapter())
      .build();
  }
}
