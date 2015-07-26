package meru.sys.lang;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class StringHelper {

    public final static void checkNullAndEmpty(String str) {

        if (isNullOrEmpty(str)) {
            throw new RuntimeException("Invalid value : '" + str + "'");
        }
    }

    public static boolean isTrue(String str) {

        return str != null && str.equalsIgnoreCase("true");
    }

    public static boolean isNullOrEmpty(String str) {

        return (str == null) || (str.trim()
                                    .length() == 0);
    }

    public static String getNonEmptyOrNull(String str) {

        if (isNullOrEmpty(str)) {
            return null;
        }

        return str;
    }

    public static String capitalizeFirstLetter(String str) {

        if (str.length() > 1) {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }

        return String.valueOf(Character.toUpperCase(str.charAt(0)));
    }

    public static String toCommaSeperatedString(String[] strings) {

        if (strings == null || strings.length == 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        String[] arrayOfString = strings;
        int j = strings.length;
        for (int i = 0; i < j; ++i) {
            String str = arrayOfString[i];
            if (builder.length() != 0) {
                builder.append(",");
            }
            builder.append(str);
        }

        return builder.toString();
    }

    public static String removeCommaSeparatedString(String string,
                                                    String removeStr) {

        removeStr = "," + removeStr + ",";
        int index = string.indexOf(removeStr);
        if (index >= 0) {
            String str = string.substring(0,
                                          index);
            str = str + string.substring(index + removeStr.length() - 1);
            return str;
        }

        return string;
    }

    public static String addCommaSeparatedString(String string,
                                                 String addStr) {

        if ((string == null) || (string.trim().equals(""))) {
            return "," + addStr + ",";
        }

        if (string.indexOf("," + addStr + ",") < 0) {
            if (string.endsWith(",")) {
                return string + addStr + ",";
            }
            return string + "," + addStr + ",";
        }

        return string;
    }

    public static Map<String, String> getProperties(String propStr,
                                                    String delim,
                                                    String assign) {

        Map<String, String> propMap = new HashMap<String, String>(1);
        String[] tokens = propStr.split("[" + delim + "]");

        for (String token : tokens) {
            String[] property = token.split("[" + assign + "]");
            propMap.put(property[0],
                        property[1]);
        }

        return propMap;
    }

    public static String[] getStrings(String delimittedString,
                                      String delimitter) {

        return delimittedString.split("[" + delimitter + "]");
    }

    public static void replaceString(StringBuilder strBuilder,
                                     String replaceStr,
                                     String replaceValue) {

        int index = strBuilder.indexOf(replaceStr);
        if (index >= 0)
            strBuilder.replace(index,
                               index + replaceStr.length(),
                               replaceValue);
    }

    public static String convertToVariableName(String str) {

        return str.replaceAll("[=-]",
                              "_");
    }

    public static String pad(String str,
                             char padChar,
                             int length) {

        int currLength = str.length();

        StringBuilder strBuilder = new StringBuilder(length);
        for (int i = 0; i < length - currLength; ++i) {
            strBuilder.append(padChar);
        }

        strBuilder.append(str);

        return strBuilder.toString();
    }

    public static String toString(Throwable throwable) {

        
        try (StringWriter strWriter = new StringWriter();
             PrintWriter printWriter = new PrintWriter(strWriter);) {

            throwable.printStackTrace(printWriter);
            
            return strWriter.toString();

        } catch (IOException e) {
            return null;
        } 
    }
}