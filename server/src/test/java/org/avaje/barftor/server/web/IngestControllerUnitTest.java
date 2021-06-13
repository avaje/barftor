package org.avaje.barftor.server.web;

import org.avaje.barftor.server.ingest.IngestService;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class IngestControllerUnitTest {

  @Test
  void ingest_() {

    // setup
    final IngestService ingestService = mock(IngestService.class);
    IngestController controller = new IngestController(ingestService);

    // act
    controller.ingest(new IngestForm());

    verify(ingestService).process(any());
  }
}
