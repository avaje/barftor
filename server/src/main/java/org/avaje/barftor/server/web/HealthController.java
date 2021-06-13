package org.avaje.barftor.server.web;

import io.avaje.http.api.Controller;
import io.avaje.http.api.Get;
import io.avaje.http.api.Path;
import io.avaje.http.api.Produces;

@Controller
@Path("health")
public class HealthController {

  @Produces("text/plain")
  @Get
  String ok() {
    return "ok";
  }

}
