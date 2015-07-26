package meru.comm.mobile.sms;

import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;

public class InMessage extends Message {

  public static final int RECEIVE_UNREAD = 0;
  public static final int RECEIVE_READ = 1;
  public static final int RECEIVE_UNSENT = 2;
  public static final int RECEIVE_SENT = 3;
  public static final int RECEIVE_ALL = 4;
  
  public static final String[] MESSAGE_TYPES = {"Unread","Read","Unsent","Sent","All"};
  
  private int mMemoryIndex;
  private int mMPRefNo;
  private int mMPMaxNo;
  private int mMPSeqNo;
  private String mMPMemoryIndex ="";
  
  public InMessage(int memoryIndex, String message) {
    super(null, message);
    mMemoryIndex = memoryIndex;
    decode(message);
  }
  
  public int getMemoryIndex() {
    return mMemoryIndex;
  }

  public Date getReceivedDate() {
    return mDate;
  }
  
  public int getMpRefNo() {
    return mMPRefNo;
  }

  public int getMpMaxNo() {
    return mMPMaxNo;
  }

  public int getMpSeqNo() {
    return mMPSeqNo;
  }

  public String getMpMemoryIndex() {
    return mMPMemoryIndex;
  }

  protected void setMpSeqNo(int mpSeqNo) {
    mMPSeqNo = mpSeqNo;
  }

  protected void setMemoryIndex(int memoryIndex) {
    mMemoryIndex = memoryIndex;
  }

  protected void setMpMemoryIndex(int memoryIndex) {
    mMPMemoryIndex += (mMPMemoryIndex.length() == 0 ? "" : ",") + mMPMemoryIndex;
  }


  
  private void decode(String message) {
    int i = Integer.parseInt(message.substring(0, 2), 16);
    int index = (i + 1) * 2;
    boolean containsUDH = ((Integer.parseInt(message.substring(index, index + 2), 16) & 0x40) != 0) ? true : false;
    
    index += 2;
    i = Integer.parseInt(message.substring(index, index + 2), 16);
    int j = index + 4;
    
    mSender = "";
    int k =0;
    for (; k<i; k+=2) {
      mSender = mSender + message.charAt(j + k + 1) + message.charAt(j + k);
    }
    mSender = "+" + mSender;
    
    if (mSender.charAt(mSender.length() - 1) == 'F') {
      mSender = mSender.substring(0, mSender.length() - 1);
    }
    
    int address = Integer.parseInt(message.substring(j - 2, j), 16);

    String text = null;
    if ((address & (1 << 6)) != 0 && (address & (1 << 5)) == 0 && (address & (1 << 4)) != 0) {
      text = toText(message.substring(j, j + k));
      mSender = "";
      int length = text.length();
      for (i=0; i<length; i++)
      {
        if ( (int) text.charAt(i) == 27) {
          mSender += DataConverter.hexToExtChar( (int) text.charAt(++i), DataConverter.CHARSET_GSM7BIT);
        }
        else {
          mSender += DataConverter.hexToChar( (int) text.charAt(i), DataConverter.CHARSET_GSM7BIT);
        }
      }
    }
    
    index = j + k + 2;
    text = "" + message.charAt(index) + message.charAt(index + 1);
    int protocol = Integer.parseInt(text, 16);
    index += 2;
    int year = Integer.parseInt("" + message.charAt(index + 1) + message.charAt(index)); 
    index += 2;
    int month = Integer.parseInt("" + message.charAt(index + 1) + message.charAt(index)); 
    index += 2;
    int day = Integer.parseInt("" + message.charAt(index + 1) + message.charAt(index)); 
    index += 2;
    int hour = Integer.parseInt("" + message.charAt(index + 1) + message.charAt(index)); 
    index += 2;
    int minute = Integer.parseInt("" + message.charAt(index + 1) + message.charAt(index)); 
    index += 2;
    int second = Integer.parseInt("" + message.charAt(index + 1) + message.charAt(index)); 
    index += 4;
    
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year + 2000);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.DAY_OF_MONTH, day);
    calendar.set(Calendar.HOUR_OF_DAY, hour);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND, second);
    mDate = calendar.getTime();
    
    int udhLength = 0;
    String udhData = "";
    
    if (containsUDH)
    {
      udhLength = Integer.parseInt(message.substring(index + 2, index + 2 + 2));
      udhData = message.substring(index + 2 + 2, index + 2 + 2 + (udhLength * 2));

      if (udhData.substring(0, 2).equalsIgnoreCase("00"))
      {
        mMPRefNo = Integer.parseInt(udhData.substring(4, 6), 16);
        mMPMaxNo = Integer.parseInt(udhData.substring(6, 8), 16);
        mMPSeqNo = Integer.parseInt(udhData.substring(8, 10), 16);
      }
    }
    
    switch (protocol & 0x0C)
    {
      case 0:
        mEncoding = ENCODING_7BIT;
        text = toText(message.substring(index + 2));
        String data = "";
        int length = text.length();
        for (i=0; i<length; i++) {
          if ((int) text.charAt(i) == 27) {
            data += DataConverter.hexToExtChar((int) text.charAt(++i), DataConverter.CHARSET_GSM7BIT);
          }
          else {
            data += DataConverter.hexToChar((int) text.charAt(i), DataConverter.CHARSET_GSM7BIT);
          }
        }
          
        if (containsUDH) {
          text = data.substring(udhLength + 2);
        }
        else {
          text = data;
        }
        break;
      case 4:
        mEncoding = ENCODING_8BIT;
        index += 2;
        text = "";
        while (index < message.length())
        {
          i = Integer.parseInt("" + message.charAt(index) + message.charAt(index + 1), 16);
          text = text + (char) i;
          index += 2;
        }
        break;
      case 8:
        mEncoding = ENCODING_UNICODE;
        index += 2;
        text = "";
        while (index < message.length())
        {
          i = Integer.parseInt("" + message.charAt(index) + message.charAt(index + 1), 16);
          j = Integer.parseInt("" + message.charAt(index + 2) + message.charAt(index + 3), 16);
          text = text + (char) ((i * 256) + j);
          index += 4;
        }
        break;
    }

    mText = text;
  }
 
  private String toText(String pdu)
  {
    int i, j;

    byte[] oldBytes = new byte[pdu.length() / 2];
    int pduLength = pdu.length()/2;
    for (i=0; i<pduLength; i++)
    {
      oldBytes[i] = (byte) (Integer.parseInt(pdu.substring(i * 2, (i * 2) + 1), 16) * 16);
      oldBytes[i] += (byte) Integer.parseInt(pdu.substring((i * 2) + 1, (i * 2) + 2), 16);
    }

    BitSet bitSet = new BitSet(pduLength * 8);
    int value1 = 0;
    for (i=0; i<pduLength; i++) {
      for (j=0; j<8; j++)
      {
        value1 = (i * 8) + j;
        if ((oldBytes[i] & (1 << j)) != 0) {
          bitSet.set(value1);
        }
      }
    }
    value1 ++;

    int value2 = value1 / 7;
    if (value2 == 0) {
      value2++;
    }

    byte[] newBytes = new byte[value2];
    for (i = 0; i < value2; i ++) {
      for (j = 0; j < 7; j ++) {
        if ((value1 + 1) > (i * 7 + j)) {
          if (bitSet.get(i * 7 + j)) {
            newBytes[i] |= (byte) (1 << j);
          }
        }
      }
    }
    String text = null;
    if (newBytes[value2 - 1] == 0) {
      text = new String(newBytes, 0, value2 - 1);
    }
    else {
      text = new String(newBytes);
    }
    return text;
  }

  
}
