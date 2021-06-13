package org.avaje.barftor.server.web;

import org.avaje.barftor.server.ingest.IngestService;
import io.avaje.http.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@Path("api/ingest")
public class IngestController {

  private static final Logger log = LoggerFactory.getLogger(IngestController.class);

  private final IngestService ingestService;

  public IngestController(IngestService ingestService) {
    this.ingestService = ingestService;
  }

  @Post
  @Form
  void ingest(IngestForm form) {
    log.info("ingesting {} ", form);
    ingestService.process(form);
  }


  @Produces("text/plain")
  @Get("ok")
  String ok() {
    return "ok";
  }


}
