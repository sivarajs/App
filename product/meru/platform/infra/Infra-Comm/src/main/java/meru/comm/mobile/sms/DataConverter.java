package meru.comm.mobile.sms;

class DataConverter {

  public static final int CHARSET_GSM7BIT = 1;

  private static final String ALPHABET =
      "\u00A5\u00E8\u00E9\u00F9\u00EC\u00F2\u00C7\n\u00D8\u00F8\r\u00C5\u00E5\u00C6\u00E6\u00DF\u00C9 !\"#\u00A4%&\'()*+,-./0123456789:;<=>?\u00A1ABCDEFGHIJKLMNOPQRSTUVWXYZ\u00C4\u00D6\u00D1\u00DC\u00BFabcdefghijklmnopqrstuvwxyz\u00E4\u00F6\u00F1\u00FC\u00E0";

  public static String charToHex(char character, int charSet) {
    switch (charSet) {
      case CHARSET_GSM7BIT :
        int length = ALPHABET.length();
        for (int i = 0; i < length; i++) {
          if (ALPHABET.charAt(i) == character) {
            return (i <= 15 ? "0" + Integer.toHexString(i) : Integer
                .toHexString(i));
          }
        }
        break;
    }
    return (Integer.toHexString((int) character).length() < 2)
        ? "0" + Integer.toHexString((int) character)
          : Integer.toHexString((int) character);
  }

  public static char hexToChar(int index, int charSet) {
    switch (charSet) {
      case CHARSET_GSM7BIT :
        if (index < ALPHABET.length()) {
          return ALPHABET.charAt(index);
        }
    }
    return '?';
  }

  public static char hexToExtChar(int ch, int charSet) {
    switch (charSet) {
      case CHARSET_GSM7BIT :
        switch (ch) {
          case 10 :
            return '\f';
          case 20 :
            return '^';
          case 40 :
            return '{';
          case 41 :
            return '}';
          case 47 :
            return '\\';
          case 60 :
            return '[';
          case 61 :
            return '~';
          case 62 :
            return ']';
          case 64 :
            return '|';
          case 101 :
            return '\u20AC';
          default:
            return '?';
        }
      default:
        return '?';
    }
  }

  public static String textToHex(String text, int charSet) {
    String outText = "";
    int length = text.length();
    for (int i=0; i<length; i++) {
      switch (text.charAt(i)) {
        case '\f' :
          outText = outText + Integer.toHexString(27) + Integer.toHexString(10);
          break;
        case '^' :
          outText = outText + Integer.toHexString(27) + Integer.toHexString(20);
          break;
        case '{' :
          outText = outText + Integer.toHexString(27) + Integer.toHexString(40);
          break;
        case '}' :
          outText = outText + Integer.toHexString(27) + Integer.toHexString(41);
          break;
        case '\\' :
          outText = outText + Integer.toHexString(27) + Integer.toHexString(47);
          break;
        case '[' :
          outText = outText + Integer.toHexString(27) + Integer.toHexString(60);
          break;
        case '~' :
          outText = outText + Integer.toHexString(27) + Integer.toHexString(61);
          break;
        case ']' :
          outText = outText + Integer.toHexString(27) + Integer.toHexString(62);
          break;
        case '|' :
          outText = outText + Integer.toHexString(27) + Integer.toHexString(64);
          break;
        case '\u20AC' :
          outText =
              outText + Integer.toHexString(27) + Integer.toHexString(101);
          break;
        default:
          outText = outText + charToHex(text.charAt(i), charSet);
          break;
      }
    }
    return outText;
  }

  public static String hexToText(String text, int charSet) {
    String outText = "";
    int length = text.length();
    
    for (int i=0; i<length; i+=2) {
      String hexChar = "" + text.charAt(i) + text.charAt(i + 1);
      int c = Integer.parseInt(hexChar, 16);
      if (c == 27) {
        i++;
        outText = outText + hexToExtChar((char) c, charSet);
      }
      else {
        outText = outText + hexToChar((char) c, charSet);
      }
    }
    return outText;
  }
}
