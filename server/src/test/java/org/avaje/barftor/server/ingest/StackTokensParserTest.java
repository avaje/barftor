package org.avaje.barftor.server.ingest;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class StackTokensParserTest {

  @Test
  void parsedStackTrace() {
    final StackTokens tokens = StackTokensParser.parse("hi 1243 there", "more stack\r\nbar");
    assertThat(tokens.firstLine()).isEqualTo("hi 1243 there");
    assertThat(tokens.parsedLine()).isEqualTo("hi <n> there");
    assertThat(tokens.parsedStackTrace()).isEqualTo("hi <n> there\nmore stack\r\nbar");
  }

  @Test
  void parseNewLines_in_message() {
    assertThat(parsedLine("foo")).isEqualTo("foo");
    assertThat(parsedLine("foo\nbar")).isEqualTo("foo\nbar");
    assertThat(parsedLine("foo\r\nbar")).isEqualTo("foo\r\nbar");
    assertThat(parsedLine("foo\nbar\nbaz")).isEqualTo("foo\nbar\nbaz");
  }

  @Test
  void parseNewLines_withNoMessage() {
    assertThat(parsedLine(null, "foo")).isEqualTo("foo");
    assertThat(parsedLine("", "foo\nbar")).isEqualTo("foo");
    assertThat(parsedLine(null, "foo\r\nbar")).isEqualTo("foo");
    assertThat(parsedLine("", "foo\nbar\nbaz")).isEqualTo("foo");
  }

  @Test
  void parseDate() {
    assertThat(parsedLine("A2020-04-01B")).isEqualTo("A<d>B");
  }

  @Test
  void parseTimestamp() {
    assertThat(parsedLine("A2020-04-01T12:00B")).isEqualTo("A<ts>B");
    assertThat(parsedLine("A2020-04-01T12:00:34B")).isEqualTo("A<ts>B");
    assertThat(parsedLine("A2020-04-01T12:00:34.123B")).isEqualTo("A<ts>B");
    assertThat(parsedLine("A2020-04-01T12:00:34.123-1B")).isEqualTo("A<ts>B");
    assertThat(parsedLine("A2020-04-01T12:00:34.123+1B")).isEqualTo("A<ts>B");
    assertThat(parsedLine("A2020-04-01T12:00:34.123+0800B")).isEqualTo("A<ts>B");
    assertThat(parsedLine("A2020-04-01T12:00:34.123+08:00B")).isEqualTo("A<ts>B");
    assertThat(parsedLine("A2020-04-01T12:00:34.123ZB")).isEqualTo("A<ts>B");
  }

  @Test
  void parseUuid() {
    assertThat(parsedLine("A" + UUID.randomUUID() + "B")).isEqualTo("A<uuid>B");
  }

  @Test
  void parseNum() {
    assertThat(parsedLine("A 123 B")).isEqualTo("A <n> B");
    assertThat(parsedLine("A:123 B")).isEqualTo("A <n> B");
    assertThat(parsedLine("A123B")).isEqualTo("A123B");
    assertThat(parsedLine("A:123B")).isEqualTo("A:123B");
  }

  @Test
  void parseNum_endOfLine() {
    assertThat(parsedLine("A 123")).isEqualTo("A <n>");
    assertThat(parsedLine("A:123")).isEqualTo("A <n>");
  }

  @Test
  void parseTimestampAndDate() {
    assertThat(parsedLine("A2020-04-01T12:00B2020-04-01C")).isEqualTo("A<ts>B<d>C");
  }

  @Test
  void parseNumTimestampAndDate() {
    assertThat(parsedLine("N 1234 A2020-04-01T12:00B2020-04-01C")).isEqualTo("N <n> A<ts>B<d>C");
  }

  private String parsedLine(String msg) {
    return parsedLine(msg, null);
  }

  private String parsedLine(String msg, String stackTrace) {
    return StackTokensParser.parse(msg, stackTrace).parsedLine();
  }
}
