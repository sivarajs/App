package meru.app;

import java.io.File;
import java.text.DecimalFormat;

public class AppProperty {

  public static final DecimalFormat FLOAT_FORMAT = new DecimalFormat("#.##");
  
  public static final String TEMPLATE_DIR = "/WEB-INF" + File.separator + "app" + File.separator + "template" + File.separator;
  public static final String TEMPLATE_DIR_MAIL = TEMPLATE_DIR + "mail" + File.separator;
  public static final String TEMPLATE_DIR_SMS = TEMPLATE_DIR + "sms" + File.separator;
}
