package org.avaje.barftor.log4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

class SimplePrintWriter extends PrintWriter {

  SimplePrintWriter() {
    super(new StringWriter(400));
  }

  @Override
  public void print(String s) {
    try {
      out.write(s);
    } catch (IOException e) {
      throw new RuntimeException("Never expected", e);
    }
  }

  @Override
  public void println() {
    try {
      out.write(System.lineSeparator());
    } catch (IOException e) {
      throw new RuntimeException("Never expected", e);
    }
  }

  @Override
  public void println(String x) {
    try {
      out.write(x);
      out.write(System.lineSeparator());
    } catch (IOException e) {
      throw new RuntimeException("Never expected", e);
    }
  }

  @Override
  public void println(Object x) {
    println(String.valueOf(x));
  }
}
