package meru.template;




public class Template {

  private static final char BINDING_VAR_INDICATOR = '#';
  private static final char BINDING_VAR_START = '{';
  private static final char BINDING_VAR_END = '}';

  private String mTemplate;
  private TemplateParserImpl mTemplateParser;

  public Template(String template) {
    mTemplate = template;
  }

  public String parse(TemplateData templateData) {

    mTemplateParser = new TemplateParserImpl(templateData,
                                             mTemplate.length());

    StringBuilder strBuilder = new StringBuilder();
    char prevChar = 0;
    boolean varStarted = false;
    for (char ch : mTemplate.toCharArray()) {

      switch (ch) {
        case BINDING_VAR_INDICATOR:
          prevChar = ch;
          break;
        case BINDING_VAR_START:

          if (prevChar == BINDING_VAR_INDICATOR) {

            varStarted = true;

            if (strBuilder.length() > 0) {
              mTemplateParser.processText(strBuilder.toString());
              strBuilder.delete(0,
                                strBuilder.length());
            }

          }
          else {
            strBuilder.append(ch);
          }

          break;
        case BINDING_VAR_END:

          if (varStarted) {
            varStarted = false;
            mTemplateParser.processVariable(strBuilder.toString());
            strBuilder.delete(0,
                              strBuilder.length());
          }

          break;
        default:
          strBuilder.append(ch);
      }

    }

    if (strBuilder.length() > 0) {
      mTemplateParser.processText(strBuilder.toString());
    }

    return mTemplateParser.getParsedData();
  }

  private class TemplateParserImpl implements TemplateParser {

    private TemplateData mTemplateData;
    private StringBuilder mStrBuilder;

    public TemplateParserImpl(TemplateData templateData,
                              int initialSize) {
      mTemplateData = templateData;
      mStrBuilder = new StringBuilder(initialSize);
    }

    @Override
    public void processText(String text) {
      mStrBuilder.append(text);
    }

    @Override
    public void processVariable(String expression) {
      mStrBuilder.append(mTemplateData.getExpressionValue(expression));
    }

    public String getParsedData() {
      return mStrBuilder.toString();
    }

  }
}
