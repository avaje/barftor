package org.avaje.barftor.server.web;

import io.avaje.http.client.HttpClientContext;
import io.avaje.http.client.JacksonBodyAdapter;
import io.avaje.inject.BeanScope;
import io.avaje.inject.BeanScopeBuilder;
import io.avaje.jex.Jex;
import io.ebean.DB;
import io.ebean.Database;
import org.avaje.barftor.server.Main;
import org.junit.jupiter.api.AfterAll;

import java.util.Random;

public class BaseWebTest {

  public static String baseUrl;
  public static Jex.Server webServer;
  public static BeanScope beanScope;

  public static HttpClientContext init(BeanScopeBuilder builder) {
    builder.withBean(Database.class, DB.getDefault());
    int port = 11000 + new Random().nextInt(1000);
    beanScope = builder.build();
    baseUrl = "http://localhost:" + port;
    webServer = new Main().run(port, beanScope);
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
