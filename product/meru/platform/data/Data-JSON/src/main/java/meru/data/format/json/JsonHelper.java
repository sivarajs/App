package meru.data.format.json;

public class JsonHelper {

    public static final String escape(String data) {
        if (data != null) {

            char[] chars = data.toCharArray();
            StringBuilder strBuilder = new StringBuilder();
            char prevCh = 0;
            for (char ch : chars) {

                switch (ch) {
                    case '\n':
                    case '\t':
                    case '\r':
                    case '\\':
                        break;
                    case 'n':
                    case 'r':
                    case 't':
                        if (prevCh != '\\') {
                            strBuilder.append(ch);
                        }
                        break;
                    default:
                        strBuilder.append(ch);

                }
                prevCh = ch;

            }
            
            return strBuilder.toString();
        }
        
        return null;
    }
}
