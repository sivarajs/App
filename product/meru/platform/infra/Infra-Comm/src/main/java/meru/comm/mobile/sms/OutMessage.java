package meru.comm.mobile.sms;

import java.util.BitSet;
import java.util.Date;

public class OutMessage extends Message {

  public OutMessage(String recipient, String message) {
    super(recipient, message);
  }

  public OutMessage(String[] recipients, String message) {
    super(recipients[0], message);
  }

  public Date getDispatchDate() {
    return mDate;
  }

  public void setDispatchDate(Date date) {
    mDate = date;
  }
  
  public void recordDispatchDate() {
    mDate = new Date();
  }
  
  public String getPDU(String smscNumber) {
    String str1, str2;
    int i, high, low;
    char c;

    String pdu = "";
    
    if (smscNumber != null) {
      if (smscNumber.length() == 0) {
        pdu += "00";
      }
      else {
        str1 = "91" + toBCD(smscNumber.substring(1));
        pdu = Integer.toHexString(str1.length() / 2);
        if (pdu.length() != 2) {
          pdu = "0" + pdu;
        }
        pdu += str1;
      }
    }
    pdu = pdu + "31";
    pdu += "00";
    String recipient = getRecipient();
    if (recipient.charAt(0) == '+') {
      recipient = toBCD(recipient.substring(1));
      str2 = Integer.toHexString(getRecipient().length() - 1);
      recipient = "91" + recipient;
    }
    else {
      recipient = toBCD(recipient);
      str2 = Integer.toHexString(getRecipient().length());
      recipient = "81" + recipient;
    }
    if (str2.length() != 2) {
      str2 = "0" + str2;
    }

    pdu += str2 + recipient + "00";
    switch (mEncoding) {
      case ENCODING_7BIT :
        if (mIsFlash) {
          pdu += "10";
        }
        else {
          pdu += "00";
        }
        break;
      case ENCODING_8BIT :
        if (mIsFlash) {
          pdu += "14";
        }
        else {
          pdu += "04";
        }
        break;
      case ENCODING_UNICODE :
        if (mIsFlash) {
          pdu += "18";
        }
        else {
          pdu += "08";
        }
        break;
    }
    pdu += formatTimeToLive();

    switch (mEncoding) {
      case ENCODING_7BIT :
        str2 = toPDU(getText());
        str1 = Integer.toHexString(getText().length());
        if (str1.length() != 2) {
          str1 = "0" + str1;
        }
        pdu += str1 + str2;
        break;
      case ENCODING_8BIT :
        str1 = getText();
        str2 = "";
        for (i = 0; i < str1.length(); i++) {
          c = str1.charAt(i);
          str2 +=
              ((Integer.toHexString((int) c).length() < 2)
                      ? "0" + Integer.toHexString((int) c)
                        : Integer.toHexString((int) c));
        }
        str1 = Integer.toHexString(getText().length());
        if (str1.length() != 2) {
          str1 = "0" + str1;
        }
        pdu += str1 + str2;
        break;
      case ENCODING_UNICODE :
        str1 = getText();
        str2 = "";
        for (i = 0; i < str1.length(); i++) {
          c = str1.charAt(i);
          high = (int) (c / 256);
          low = c % 256;
          str2 += ((Integer.toHexString(high).length() < 2)
                      ? "0" + Integer.toHexString(high)
                        : Integer.toHexString(high));
          str2 += ((Integer.toHexString(low).length() < 2)
                      ? "0" + Integer.toHexString(low)
                        : Integer.toHexString(low));
        }
        str1 = Integer.toHexString(getText().length() * 2);
        if (str1.length() != 2) {
          str1 = "0" + str1;
        }
        pdu += str1 + str2;
        break;
    }
    return pdu.toUpperCase();
  }

  private String toBCD(String smscNo) {
    if ((smscNo.length() % 2) != 0) {
      smscNo += "F";
    }

    String bcd = "";
    int length = smscNo.length();
    for (int i=0; i<length; i+=2) {
      bcd = bcd + smscNo.charAt(i + 1) + smscNo.charAt(i);
    }
    return bcd;
  }

  private String formatTimeToLive() {
    String timeToLive;
    if (mTimeToLive == -1) {
      timeToLive = "FF";
    }
    else {
      if (mTimeToLive <= 12) {
        timeToLive = Integer.toHexString((mTimeToLive * 12) - 1);
      }
      else if (mTimeToLive <= 24) {
        timeToLive = Integer.toHexString(((mTimeToLive - 12) * 2) + 143);
      }
      else if (mTimeToLive <= 720) {
        timeToLive = Integer.toHexString((mTimeToLive / 24) + 166);
      }
      else {
        timeToLive = Integer.toHexString((mTimeToLive / 168) + 192);
      }
      if (timeToLive.length() != 2) {
        timeToLive = "0" + timeToLive;
      }
      if (timeToLive.length() > 2) {
        timeToLive = "FF";
      }
    }
    return timeToLive;
  }
  
  private String toPDU(String message)
  {
    String pdu, str1;
    byte[] oldBytes, newBytes;
    BitSet bitSet;
    int i, j, value1, value2;

    str1 = "";    
    message = DataConverter.textToHex(message, DataConverter.CHARSET_GSM7BIT);
    int length = message.length();
    for (i=0; i<length; i+=2)
    {
      j = (Integer.parseInt("" + message.charAt(i), 16) * 16) + Integer.parseInt("" + message.charAt(i + 1), 16);
      str1 += (char) j;
    }
    message = str1; 
    oldBytes = message.getBytes();
    bitSet = new BitSet(message.length() * 8);

    value1 = 0;
    for (i = 0; i < message.length(); i ++) {
      for (j = 0; j < 7; j ++)
      {
        value1 = (i * 7) + j;
        if ((oldBytes[i] & (1 << j)) != 0) bitSet.set(value1);
      }
    }
    value1 ++;

    if (((value1 / 56) * 56) != value1) {
      value2 = (value1 / 8) + 1;
    }
    else {
      value2 = value1 / 8;
    }
    if (value2 == 0) {
      value2 = 1;
    }

    newBytes = new byte[value2];
    for (i=0; i<value2; i++) {
      for (j=0; j<8; j++) {
        if ((value1 + 1) > ((i * 8) + j)) {
          if (bitSet.get(i * 8 + j)) {
            newBytes[i] |= (byte) (1 << j);
          }
        }
      }
    }
    pdu = "";
    for (i = 0; i < value2; i ++)
    {
      str1 = Integer.toHexString((int) newBytes[i]);
      if (str1.length() != 2) {
        str1 = "0" + str1;
      }
      str1 = str1.substring(str1.length() - 2, str1.length());
      pdu += str1;
    }
    return pdu;
  }
}
