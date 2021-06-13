package org.avaje.barftor.server.ingest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StackTokensParser {

  private static final Pattern NL = Pattern.compile("\\R");

  private static final Pattern DATE = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
  private static final Pattern TS = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}([+-:Z0-9]*)?");
  private static final Pattern UUID = Pattern.compile("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})");
  private static final Pattern NUM = Pattern.compile("([ :][0-9]*($|\\s))");


  static StackTokens parse(String message, String stackTrace) {

    final String firstLine;
    final String remainingLines;

    if (message != null && !message.isEmpty()) {
      firstLine = message;
      remainingLines = stackTrace;
    } else if (stackTrace == null || stackTrace.isEmpty()) {
      return null;

    } else {
      final Matcher matcher = NL.matcher(stackTrace);
      if (matcher.find()) {
        final int start = matcher.start();
        firstLine = stackTrace.substring(0, start);
        remainingLines = stackTrace.substring(start).trim();
      } else {
        firstLine = stackTrace;
        remainingLines = null;
      }
    }
    String parsedLine = parseFirstLine(firstLine);
    String parsedStackTrace = remainingLines == null ? parsedLine : parsedLine + '\n' + remainingLines;

    return new StackTokens(firstLine, parsedLine, remainingLines, stackTrace, parsedStackTrace);
  }

  static String parseFirstLine(String firstLine) {
    String line = firstLine;
    line = UUID.matcher(line).replaceAll("<uuid>");
    line = TS.matcher(line).replaceAll("<ts>");
    line = DATE.matcher(line).replaceAll("<d>");
    line = NUM.matcher(line).replaceAll(" <n> ");
    // escape timestamps, uuids, dates, numbers
    return line.trim();
  }

}
