package org.avaje.barftor.server.ingest;

public class StackTokens {

  private final String stackTrace;
  private final String firstLine;
  private final String parsedLine;
  private final String remainingLines;
  private final String parsedStackTrace;

  StackTokens(String firstLine, String parsedLine, String remainingLines, String stackTrace, String parsedStackTrace) {
    this.firstLine = firstLine;
    this.parsedLine = parsedLine;
    this.remainingLines = remainingLines;
    this.stackTrace = stackTrace;
    this.parsedStackTrace = parsedStackTrace;
  }

  public String stackTrace() {
    return stackTrace;
  }

  public String firstLine() {
    return firstLine;
  }

  public String parsedLine() {
    return parsedLine;
  }

  public String remainingLines() {
    return remainingLines;
  }

  public String parsedStackTrace() {
    return parsedStackTrace;
  }

}
