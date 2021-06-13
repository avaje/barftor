package org.avaje.barftor.log4j;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

import java.util.List;

/**
 * Register the Barftor Log4J appender.
 */
public class BarftorRegister {

  /**
   * Register with the given application name, environment name and url.
   */
  public static void registerWith(String appName, String envName, String url) {

    org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger) LogManager.getRootLogger();

    BarftorAppender appender = new BarftorAppender(appName, envName, url);
    appender.start();

    final LoggerContext lc = logger.getContext();
    final Configuration config = lc.getConfiguration();
    config.addAppender(appender);

    AppenderRef ref = AppenderRef.createAppenderRef("Barftor", null, null);
    AppenderRef[] refs = new AppenderRef[] {ref};

    LoggerConfig loggerConfig = LoggerConfig.createLogger(true, Level.WARN, "barftor", "true", refs, null, config, null );
    loggerConfig.addAppender(appender, null, null);
    config.addLogger("barftor", loggerConfig);
    lc.updateLoggers();

    //lc.getConfiguration().addLoggerAppender(logger, appender);

//    config.addAppender(appender);
//    lc.getRootLogger().addAppender(config.getAppender(appender.getName()));
//    lc.updateLoggers();


//    LoggerContext context = (LoggerContext) LogManager.getContext(false);
//
//    Configuration config = context.getConfiguration();
//    //config.getRootLogger().addAppender(appender, Level.WARN, null);
//
//    final LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
    final List<AppenderRef> appenderRefs = loggerConfig.getAppenderRefs();
//
//    config.addLoggerAppender(context.getRootLogger(), appender);
//
////    loggerConfig.addAppender(appender, Level.WARN, null);
////    context.updateLoggers(config);
////
////    context.reconfigure();
////    context.updateLoggers();
//
//    final List<AppenderRef> appenderRefs2 = loggerConfig.getAppenderRefs();
    System.out.println("asd");
  }
}
