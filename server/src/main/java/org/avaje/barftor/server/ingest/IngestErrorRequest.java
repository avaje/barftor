package org.avaje.barftor.server.ingest;

public class IngestErrorRequest {

  private final String hash;
  private final StackTokens tokens;
  private final String app;
  private final String env;

  public IngestErrorRequest(String hash, StackTokens tokens, String app, String env) {
    this.hash = hash;
    this.tokens = tokens;
    this.app = app;
    this.env = env;
  }

  public String hash() {
    return hash;
  }

  public StackTokens tokens() {
    return tokens;
  }

  public String app() {
    return app;
  }

  public String env() {
    return env;
  }
}
