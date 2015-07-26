package meru.comm.mobile.sms;

import java.util.Date;

public class Message {

  public static final int ENCODING_7BIT = 1;
  public static final int ENCODING_8BIT = 2;
  public static final int ENCODING_UNICODE = 3;

  public static final int TYPE_INCOMING = 1;
  public static final int TYPE_OUTGOING = 2;
  public static final int TYPE_STATUS_REPORT = 3;

  protected String mId;
  protected Date mDate;

  protected String mSender;
  protected String mRecipient;
  protected String mText;
  protected int mEncoding;

  protected int mTimeToLive; // in hours
  protected boolean mIsFlash;

  public Message(String recipient, String message) {
    mRecipient = recipient;
    mText = message;
    mEncoding = ENCODING_7BIT;
    mDate = new Date();
  }

  public Message(String text) {
    mText = text;
    mEncoding = ENCODING_7BIT;
  }

  public String getId() {
    return mId;
  }

  public Date getDate() {
    return mDate;
  }

  public String getSender() {
    return mSender;
  }

  public String getRecipient() {
    return mRecipient;
  }

  public String getText() {
    return mText;
  }

  public String getHexText() {
    return DataConverter.textToHex(mText, DataConverter.CHARSET_GSM7BIT);
  }

  public int getEncoding() {
    return mEncoding;
  }

  public void setId(String id) {
    mId = id;
  }

  public void setText(String text) {
    mText = text;
  }

  public void addText(String text) {
    mText += text;
  }

  public void setDate(Date date) {
    mDate = date;
  }

  public void setEncoding(int encoding) {
    mEncoding = encoding;
  }

  public int getTimeToLive() {
    return mTimeToLive;
  }

  public void setTimeToLive(int timeToLive) {
    mTimeToLive = timeToLive;
  }

  public boolean isFlash() {
    return mIsFlash;
  }

  public void setFlash(boolean isFlash) {
    mIsFlash = isFlash;
  }

  // message type is SMS-DELIVER
  public static boolean isUserMessage(String pdu) {
    int index1 = Integer.parseInt(pdu.substring(0, 2), 16);
    int index = (index1 + 1) * 2;

    index1 = Integer.parseInt(pdu.substring(index, index + 2), 16);
    return ((index1 & 0x03) == 0);
  }

  // message type is SMS-STATUS-REPORT
  public static boolean isStatusMessage(String pdu) {
    String text = pdu.substring(0, 2);
    int index1 = Integer.parseInt(text, 16);
    int index = (index1 + 1) * 2;

    text = pdu.substring(index, index + 2);
    index1 = Integer.parseInt(text, 16);
    return ((index1 & 0x02) == 2);
  }

}
