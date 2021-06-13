import io.ebean.docker.commands.PostgresConfig;
import io.ebean.docker.commands.PostgresContainer;

public class StartPostgres {

  public static void main(String[] args) {

    PostgresConfig config = new PostgresConfig("13");
    config.setPort(5433);
    config.setDbName("barftor");
    config.setUser("barftor");
    config.setPassword("barftor");
    config.setContainerName("pg13");
    //config.setExtensions("hstore,pgcrypto");

    PostgresContainer container = new PostgresContainer(config);
    container.startWithDropCreate();
  }
}
