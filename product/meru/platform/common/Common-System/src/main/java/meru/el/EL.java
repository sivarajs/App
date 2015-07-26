package meru.el;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import meru.sys.lang.ClassHelper;

public class EL {

    private static final char BINDING_VAR_INDICATOR = '#';
    private static final char BINDING_VAR_START = '{';
    private static final char BINDING_VAR_END = '}';

    public static final String BINDING_START = "" + BINDING_VAR_INDICATOR
            + BINDING_VAR_START;
    public static final String BINDING_END = "" + BINDING_VAR_END;

    public static boolean isBindVariable(String var) {

        return var.startsWith(BINDING_START) && var.endsWith(BINDING_END);

    }

    public static boolean containsBindVariable(String var) {

        return var.indexOf(BINDING_START) != -1
                && var.indexOf(BINDING_END) != -1;

    }

    public static String getBindVariable(String var) {

        if (!isBindVariable(var)) {
            return var;
        }

        return var.substring(BINDING_START.length(),
                             var.lastIndexOf(BINDING_END));

    }

    public static void parseText(String text, ExpressionParser parser) {
        parseTextInternal(text, parser);
    }

    private static void parseTextInternal(String text, ExpressionParser parser) {

        StringBuilder strBuilder = new StringBuilder();
        char prevChar = 0;
        boolean varStarted = false;
        for (char ch : text.toCharArray()) {

            switch (ch) {
            case BINDING_VAR_INDICATOR:
                if (prevChar == BINDING_VAR_INDICATOR) {
                    strBuilder.append(prevChar);
                }
                prevChar = ch;
                break;
            case BINDING_VAR_START:

                if (prevChar == BINDING_VAR_INDICATOR) {

                    varStarted = true;

                    if (strBuilder.length() > 0) {
                        parser.readText(strBuilder.toString());
                        strBuilder.delete(0, strBuilder.length());
                    }

                }
                else {
                    strBuilder.append(ch);
                }

                prevChar = 0;
                break;
            case BINDING_VAR_END:
                
                if (varStarted) {
                    varStarted = false;
                    prevChar = 0;
                    parser.readVariable(strBuilder.toString());
                    strBuilder.delete(0, strBuilder.length());
                }
                else {
                    strBuilder.append(ch);
                }
                prevChar = 0;
                break;
            default:
                
                if (prevChar == BINDING_VAR_INDICATOR) {
                    strBuilder.append(prevChar);
                }
                
                strBuilder.append(ch);
                prevChar = 0;
            }

        }

        if (strBuilder.length() > 0) {
            parser.readText(strBuilder.toString());
        }

    }

    public static String parseText(String text, final Object resource) {
        final StringBuilder strBuilder = new StringBuilder();
        parseText(text, new ExpressionParser() {

            @Override
            public void readText(String text) {
                strBuilder.append(text);
            }

            @Override
            public void readVariable(String variable) {
                Object value = ClassHelper.getFieldValue(resource, variable);
                if (value != null) {
                    strBuilder.append(value);
                }
            }

        });

        return strBuilder.toString();
    }

    public static String parseText(String text,
                                   final Map<String, String> propertis) {
        final StringBuilder strBuilder = new StringBuilder();
        parseText(text, new ExpressionParser() {

            @Override
            public void readText(String text) {
                strBuilder.append(text);
            }

            @Override
            public void readVariable(String variable) {

                String value = propertis.get(propertis);
                if (value != null) {
                    strBuilder.append(value);
                }
            }

        });

        return strBuilder.toString();
    }

    public static List<String> getBindVariables(String str) {
        List<String> variableList = new ArrayList<String>(1);

        int sindex = 0;
        int eindex = 0;
        int eindexAdd = BINDING_START.length();
        while ((sindex = str.indexOf(BINDING_START, sindex)) >= 0) {

            eindex = str.indexOf(BINDING_END, sindex + eindexAdd);

            if (eindex >= 0) {
                String variable = str.substring(sindex + eindexAdd, eindex);
                variableList.add(variable);
                sindex = eindex;
            }
            else {
                break;
            }
        }

        return variableList;
    }

    public interface ExpressionParser {
        void readText(String text);

        void readVariable(String variable);
    }

    public static void main(String[] args) {
        String str = "aa#{variable1} dd #{variable2}#{variable3}dd";
        System.out.println(getBindVariables(str));
    }

}
