package meru.template;


public class TemplateEngine {

  
  public static String getText(String templatePath, Object object) {
    Template template = new Template(templatePath);
    return template.parse(new TemplateSingleData(object));
  }
  
  public static String getText(String templatePath, TemplateData templateData) {
    
      Template template = new Template(templatePath);
      return template.parse(templateData);
  }
  
}
