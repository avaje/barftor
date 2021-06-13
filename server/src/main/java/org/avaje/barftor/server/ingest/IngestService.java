package org.avaje.barftor.server.ingest;

import org.avaje.barftor.server.data.Repository;
import org.avaje.barftor.server.data.domain.DErrorStack;
import org.avaje.barftor.server.web.IngestForm;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class IngestService {

  private final Repository repository;

  public IngestService(Repository repository) {
    this.repository = repository;
  }

  public void process(IngestForm form) {

    var tokens = StackTokensParser.parse(form.message, form.stackTrace);
    final String hash = Md5.hash(tokens.parsedStackTrace());

    final IngestErrorRequest request = new IngestErrorRequest(hash, tokens, form.app, form.env);

    final Optional<DErrorStack> byHash = repository.findByHash(hash);
    if (byHash.isEmpty()) {
      addNewError(request);
    } else {
      addExistingError(byHash.get(), request);
    }
  }

  private void addExistingError(DErrorStack dErrorStack, IngestErrorRequest request) {
    repository.createNewInstance(dErrorStack, request);
  }

  private void addNewError(IngestErrorRequest request) {
    repository.createNewError(request);
  }

}
