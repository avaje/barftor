import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;
import java.io.IOException;

public class GenerateDbMigration {

  /**
   * Generate the next "DB schema DIFF" migration.
   */
  public static void main(String[] args) throws IOException {

    DbMigration dbMigration = DbMigration.create();
    dbMigration.addPlatform(Platform.POSTGRES);
    dbMigration.addPlatform(Platform.MYSQL);
    dbMigration.addPlatform(Platform.SQLITE);
    dbMigration.setIncludeIndex(true);
    dbMigration.setPathToResources("server/src/main/resources");

    dbMigration.generateMigration();
  }
}
