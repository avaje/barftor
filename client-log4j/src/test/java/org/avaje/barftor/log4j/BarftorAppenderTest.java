package org.avaje.barftor.log4j;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.impl.MutableLogEvent;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.junit.jupiter.api.Test;

class BarftorAppenderTest {

  @Test
  void test() {

    Throwable e = new RuntimeException("Not very interesting");

    MutableLogEvent event = new MutableLogEvent();
    event.setLevel(Level.WARN);
    event.setLoggerName("org.foo.MyService");
    final StringFormattedMessage msg = new StringFormattedMessage("Hello message id:%s", "65776", e);
    event.setMessage(msg);

    BarftorAppender barftorAppender = new BarftorAppender();
    barftorAppender.append(event);
  }
}
