package org.avaje.barftor.server.data;

import org.avaje.barftor.server.data.domain.DErrorInstance;
import org.avaje.barftor.server.data.domain.DErrorStack;
import org.avaje.barftor.server.data.domain.query.QDErrorStack;
import org.avaje.barftor.server.ingest.IngestErrorRequest;
import io.ebean.Database;
import io.ebean.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class Repository {

  private final Database database;

  public Repository(Database database) {
    this.database = database;
  }

  public Optional<DErrorStack> findByHash(String hash) {
    return new QDErrorStack()
      .hash.eq(hash)
      .findOneOrEmpty();
  }

  @Transactional
  public void createNewError(IngestErrorRequest request) {
    var tokens = request.tokens();
    var errorStack = new DErrorStack(request.hash(), tokens.parsedLine(), tokens.remainingLines());
    database.save(errorStack);

    addNewInstance(errorStack, request);
  }

  @Transactional
  public void createNewInstance(DErrorStack errorStack, IngestErrorRequest request) {
    addNewInstance(errorStack, request);
  }

  private void addNewInstance(DErrorStack errorStack, IngestErrorRequest request) {
    var tokens = request.tokens();
    var newInst = new DErrorInstance(tokens.firstLine(), errorStack, request.app(), request.env());
    database.save(newInst);
  }

}
