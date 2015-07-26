package meru.logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import meru.sys.JVM;
import meru.sys.lang.StringHelper;

public class SimpleFormatter extends Formatter {

  @Override
  public String format(LogRecord record) {

    StringBuilder builder = new StringBuilder();
    /*builder.append(SystemDate.toDateString(record.getMillis()))
           .append(" ");*/
    builder.append(record.getLevel())
           .append(" ");
    builder.append(record.getMessage());

    if (record.getThrown() != null) {
      builder.append(JVM.SystemProperty.NEW_LINE);
      builder.append(StringHelper.toString(record.getThrown()));
    }

    builder.append(JVM.SystemProperty.NEW_LINE);

    return builder.toString();
  }
}
