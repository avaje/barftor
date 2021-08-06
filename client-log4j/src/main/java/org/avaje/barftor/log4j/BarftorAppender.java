package org.avaje.barftor.log4j;

import io.avaje.http.client.HttpClientContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.message.Message;

/**
 * Log4J appender that sends errors and warnings to Barftor service.
 */
@Plugin(name = "Barftor", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
public class BarftorAppender extends AbstractAppender {

  private final String appName;
  private final String envName;
  private final HttpClientContext httpClient;

  public BarftorAppender() {
    this(
      System.getProperty("app.name", "notSet"),
      System.getProperty("app.env", "notSet"),
      System.getProperty("barftor.url", "http://localhost:8905/api/ingest")
    );
  }

  public BarftorAppender(String appName, String envName, String url) {
    super("Barftor", null, null, true, Property.EMPTY_ARRAY);
    this.appName = appName;
    this.envName = envName;
    this.httpClient = HttpClientContext.newBuilder()
      .baseUrl(url)
      .build();
  }

  @Override
  public String toString() {
    return "Barftor appName:" + appName + " envName:" + envName;
  }

  @Override
  public void append(LogEvent event) {
    if (event.getLevel().isMoreSpecificThan(Level.WARN)) {
      sendEvent(event);
    }
  }

  void sendEvent(LogEvent event) {
    final Message eventMessage = event.getMessage();
    final String message = eventMessage.getFormattedMessage();
    final Throwable throwable = eventMessage.getThrowable();
    String stackString = stackString(throwable);

    System.out.println("sending ... " + message);
    httpClient.request()
      .formParam("app", appName)
      .formParam("env", envName)
      .formParam("message", message)
      .formParam("stackTrace", stackString)
      .POST()
      .asVoid();
  }

  private String stackString(Throwable throwable) {
    if (throwable == null) {
      return "";
    } else {
      SimplePrintWriter writer = new SimplePrintWriter();
      throwable.printStackTrace(writer);
      return writer.toString();
    }
  }

  /**
   * Only used if registered via log4j xml.
   */
  @PluginBuilderFactory
  public static BarftorAppender.Builder newBuilder() {
    return new BarftorAppender.Builder();
  }

  public static class Builder<B extends BarftorAppender.Builder<B>> implements org.apache.logging.log4j.core.util.Builder<BarftorAppender> {

    @PluginBuilderAttribute
    private String name;

    public BarftorAppender.Builder setName(final String name) {
      this.name = name;
      return this;
    }

    @Override
    public BarftorAppender build() {
      return new BarftorAppender();
    }
  }
}
