package meru.logger;

import java.util.logging.Logger;

public class LoggerFactory {

  public static Logger getLogger(String loggerName) {
    
    return Logger.getLogger(loggerName);
    
  }
  
}
