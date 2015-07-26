package meru.comm.mobile.sms;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import meru.sys.JVM;

public class Device {

  protected static final String REPLACE_TOKEN = "$";

  protected static final String RESPONSE_NO_VALUE_OR_ERROR =
      "\\s*[\\p{ASCII}]*\\s+ERROR\\s+";
  protected static final String RESPONSE_NO_VALUE_OR_OK =
      "\\s*[\\p{ASCII}]*\\s+OK\\s";

  protected static final String RESPONSE_OK = "\\s+OK\\s+";
  protected static final String RESPONSE_OK_CR = "OK\r";
  protected static final String RESPONSE_ERROR = "ERROR";

  protected static final String RESPONSE_S = "\\s+";
  protected static final String NOT_APPLICABLE = "N/A";

  protected static final String RESPONSE_RESET_NOT_SUPPORTED = "+CFUN: (0)";

  protected static final String COMMAND_SYNC = "AT\r";
  protected static final String COMMAND_RESET = "AT+CFUN=1\r";
  protected static final String COMMAND_RESET_SUPPORTED = "AT+CFUN=?\r";

  protected static final String COMMAND_ECHO_OFF = "ATE0\r";
  protected static final String COMMAND_IS_ALIVE = "AT\r";
  protected static final String COMMAND_WAITING_FOR_PIN = "AT+CPIN?\r";
  protected static final String COMMAND_SIM_PIN = "SIM PIN";
  protected static final String COMMAND_VERIFY_PIN =
      "AT+CPIN=\"" + REPLACE_TOKEN + "\"\r";
  protected static final String COMMAND_VERBOSE_ERRORS = "AT+CMEE=1\r";
  protected static final String COMMAND_PDU_MODE = "AT+CMGF=0\r";
  protected static final String COMMAND_DISABLE_INDICATIONS =
      "AT+CNMI=0,0,0,0\r";
  protected static final String COMMAND_MANUFACTURER = "AT+CGMI\r";
  protected static final String COMMAND_MODEL = "AT+CGMM\r";
  protected static final String COMMAND_SERIAL_NUMBER = "AT+CGSN\r";
  protected static final String COMMAND_IMSI = "AT+CIMI\r";
  protected static final String COMMAND_SOFTWARE_VERSION = "AT+CGMR\r";
  protected static final String COMMAND_BATTERY_LEVEL = "AT+CBC\r";
  protected static final String COMMAND_SIGNAL_LEVEL = "AT+CSQ\r";
  protected static final String COMMAND_USE_PHONE_MEMORY = "AT+CPMS=\"ME\"\r";
  protected static final String COMMAND_USE_SIM = "AT+CPMS=\"SM\"\r";
  protected static final String COMMAND_COMMAND_MODE = "+++";
  protected static final String COMMAND_KEEP_LINK_OPEN = "AT+CMMS=1\r";
  protected static final String COMMAND_CONTENT_LENGTH =
      "AT+CMGS=" + REPLACE_TOKEN + "\r";
  protected static final String COMMAND_RECEIVE_MESSAGE =
      "AT+CMGL=" + REPLACE_TOKEN + "\r";
  protected static final String COMMAND_DELETE_MESSAGE =
      "AT+CMGD=" + REPLACE_TOKEN + "\r";

  protected SMSSerialPort mSerialPort;
  //private String mPin;
  private String mSMSCNumber;

  public Device(String port, int baudRate) {
    this(port, baudRate, "0000", "");
  }

  public Device(SMSSerialPort serialPort) {
    this(serialPort, "0000", "");
  }

  public Device(String port, int baudRate, String pin) {
    this(port, baudRate, pin, "");
  }

  public Device(String port, int baudRate, String pin, String smscNumber) {
    this(new SMSSerialPort(port, baudRate), pin, smscNumber);
  }

  public Device(SMSSerialPort serialPort, String pin, String smscNumber) {
    mSerialPort = serialPort;
    //mPin = pin;
    mSMSCNumber = smscNumber;
    
    reset();
    sync();
    echoOff();
    if (!isAlive()) {
      throw new RuntimeException("The device is not responding");
    }
    echoOff();
    setVerboseErrors();
    if (!setPDUMode()) {
      throw new RuntimeException("The PDU mode could not be set");
    }
    if (!disableIndications()) {
      //throw new RuntimeException("Indications could not be disabled");
    }
    
  }

  public void sync() {
    mSerialPort.send(COMMAND_SYNC);
    mSerialPort.clear();
    mSerialPort.send(COMMAND_SYNC);
    mSerialPort.clear();
    mSerialPort.send(COMMAND_SYNC);
    mSerialPort.clear();
  }

  public static void sync(SMSSerialPort serialPort) {
    serialPort.send(COMMAND_SYNC);
    serialPort.clear();
    serialPort.send(COMMAND_SYNC);
    serialPort.clear();
    serialPort.send(COMMAND_SYNC);
    serialPort.clear();
  }
  
  public void reset() {
    mSerialPort.send(COMMAND_RESET);
    try {
      Thread.sleep(10000);
    } catch (Exception e) {
    }
    mSerialPort.clear();
  }

  public static void reset(SMSSerialPort serialPort) {
    serialPort.send(COMMAND_RESET);
    try {
      Thread.sleep(10000);
    } catch (Exception e) {
    }
    serialPort.clear();
  }
  
  public void echoOff() {
    mSerialPort.send(COMMAND_ECHO_OFF);
    mSerialPort.clear();
  }

  public static void echoOff(SMSSerialPort serialPort) {
    serialPort.send(COMMAND_ECHO_OFF);
    serialPort.clear();
  }

  public boolean isAlive() {
    mSerialPort.send(COMMAND_IS_ALIVE);
    String response = mSerialPort.getResponse();
    return (response.matches(RESPONSE_NO_VALUE_OR_OK));
  }

  public boolean isWaitingForPin() {
    mSerialPort.send(COMMAND_WAITING_FOR_PIN);
    return (mSerialPort.getResponse().indexOf(COMMAND_SIM_PIN) >= 0);
  }

  public boolean verifyPin(String pin) {
    mSerialPort.send(COMMAND_VERIFY_PIN.replace(REPLACE_TOKEN, pin));
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException("Could not verify the pin");
    }
    if (mSerialPort.getResponse().indexOf("OK") > 0) {
      try {
        Thread.sleep(10000);
      } catch (Exception e) {
      }
      return true;
    }
    return false;
  }

  public boolean setVerboseErrors() {
    mSerialPort.send(COMMAND_VERBOSE_ERRORS);
    return (mSerialPort.getResponse().matches(RESPONSE_OK));
  }

  public boolean setPDUMode() {
    mSerialPort.send(COMMAND_PDU_MODE);
    return (mSerialPort.getResponse().matches(RESPONSE_OK));
  }

  public boolean disableIndications() {
    mSerialPort.send(COMMAND_DISABLE_INDICATIONS);
    return (mSerialPort.getResponse().matches(RESPONSE_OK));
  }

  private static String processResponse(String response) {

    if (response.matches(RESPONSE_NO_VALUE_OR_ERROR)) {
      return NOT_APPLICABLE;
    }
    response = response.replaceAll(RESPONSE_OK, "");
    return response.replaceAll(RESPONSE_S, "");
  }

  public String getManufacturer() {
    mSerialPort.send(COMMAND_MANUFACTURER);
    return processResponse(mSerialPort.getResponse());
  }

  public static String getManufacturer(SMSSerialPort serialPort)
      {
    reset(serialPort);
    sync(serialPort);
    echoOff(serialPort);
    serialPort.send(COMMAND_MANUFACTURER);
    return processResponse(serialPort.getResponse());
  }

  public String getModel() {
    mSerialPort.send(COMMAND_MODEL);
    return processResponse(mSerialPort.getResponse());
  }

  public static String getModel(SMSSerialPort serialPort)
      {
    serialPort.send(COMMAND_MODEL);
    return processResponse(serialPort.getResponse());
  }

  public String getSerialNo() {
    mSerialPort.send(COMMAND_SERIAL_NUMBER);
    return processResponse(mSerialPort.getResponse());
  }

  /**
   * IMSI (International Mobile Subscriber Identity)
   */
  public String getImsi() {
    mSerialPort.send(COMMAND_IMSI);
    return processResponse(mSerialPort.getResponse());
  }

  /**
   * GSM device software version
   */
  public String getSoftwareVersion() {
    mSerialPort.send(COMMAND_SOFTWARE_VERSION);
    return processResponse(mSerialPort.getResponse());
  }

  public int getBatteryLevel() {
    mSerialPort.send(COMMAND_BATTERY_LEVEL);
    String response = processResponse(mSerialPort.getResponse());
    if (response == NOT_APPLICABLE) {
      return 0;
    }
    StringTokenizer tokenizer = new StringTokenizer(response, ":,");
    tokenizer.nextToken();
    tokenizer.nextToken();
    return Integer.parseInt(tokenizer.nextToken());
  }

  public int getSignalLevel() {
    mSerialPort.send(COMMAND_SIGNAL_LEVEL);
    String response = processResponse(mSerialPort.getResponse());
    if (response == NOT_APPLICABLE) {
      return 0;
    }
    StringTokenizer tokenizer = new StringTokenizer(response, ":,");
    tokenizer.nextToken();
    String value = tokenizer.nextToken();
    if (value == null) {
      return 0;
    }
    return Integer.parseInt(value.trim()) * 100 / 31;
  }

  /**
   * the preferred message storage to Memory.
   */
  public boolean usePhoneMemory() {
    mSerialPort.send(COMMAND_USE_PHONE_MEMORY);
    return (mSerialPort.getResponse().matches(RESPONSE_NO_VALUE_OR_OK));
  }

  /**
   * the preferred message storage to SIM.
   */
  public boolean useSIM() {
    mSerialPort.send(COMMAND_USE_SIM);
    return (mSerialPort.getResponse().matches(RESPONSE_NO_VALUE_OR_OK));
  }

  /**
   * Switches GSM device to command mode
   */
  public void toCommandMode() {
    mSerialPort.send(COMMAND_COMMAND_MODE);
    mSerialPort.clear();
  }

  public boolean keepLinkOpen() {
    mSerialPort.send(COMMAND_KEEP_LINK_OPEN);
    return (mSerialPort.getResponse().matches(RESPONSE_OK));
  }

  public void sendAdditionalChars() {

  }

  public void sendPDU(int size, String pdu) {

    int responseRetries;
    String response;
    String data =
        COMMAND_CONTENT_LENGTH.replace(REPLACE_TOKEN, String.valueOf(size));
    int errorRetries = 0;
    while (true) {
      responseRetries = 0;
      mSerialPort.send(data);
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        throw new RuntimeException("Could not send the message");
      }
      while (!mSerialPort.dataAvailable()) {
        responseRetries++;
        if (responseRetries == 4) {
          throw new RuntimeException("No response from the device");
        }
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          throw new RuntimeException("Could not send the message");
        }
      }
      responseRetries = 0;
      mSerialPort.clear();
      mSerialPort.send(pdu);
      mSerialPort.send((char) 26);
      sendAdditionalChars();
      response = mSerialPort.getResponse();
      while (response.length() == 0) {
        if (++responseRetries == 4) {
          throw new RuntimeException("No response from the device");
        }
        response = mSerialPort.getResponse();
      }
      if (response.indexOf(RESPONSE_OK_CR) >= 0) {
        break;
      }
      else if (response.indexOf(RESPONSE_ERROR) != -1) {
        if (++errorRetries == 4) {
          throw new RuntimeException("The message could not be sent. "+response);
        }
      }
    }
  }

  public void sendMessage(OutMessage outMessage) {
    sendMessages(new OutMessage[] { outMessage });
  }

  public void sendMessages(OutMessage[] outMessages) {
    keepLinkOpen();
    for (int i = 0; i < outMessages.length; i++) {
      String pdu = outMessages[i].getPDU(mSMSCNumber);
      int pduLength = pdu.length();
      pduLength /= 2;

      if (mSMSCNumber != null) {
        if (mSMSCNumber.length() == 0) {
          pduLength--;
        }
        else {
          pduLength -= ((mSMSCNumber.length() - 1) / 2);
          pduLength -= 2;
        }
      }

      sendPDU(pduLength, pdu);
      outMessages[i].recordDispatchDate();
    }
  }

  private String receivePDUs(int messageType) {
    mSerialPort.send(COMMAND_RECEIVE_MESSAGE.replace(REPLACE_TOKEN, String
        .valueOf(messageType)));
    return mSerialPort.getResponse();
  }

  public InMessage[] readMessages(int messageType) {
    toCommandMode();
    String receivedText = receivePDUs(messageType);
    receivedText = receivedText.replaceAll(RESPONSE_OK, "\nOK");

    BufferedReader bufReader = null;
    StringReader strReader = null;

    strReader = new StringReader(receivedText);
    bufReader = new BufferedReader(strReader);

    ArrayList<InMessage> messageList = new ArrayList<InMessage>();
    try {
      String line = bufReader.readLine().trim();

      InMessage mpMessage = null;

      while ((line = bufReader.readLine()) != null && line.length() > 0
             && !line.equalsIgnoreCase("OK")) {
        int colonIndex = line.indexOf(':');
        int commaIndex = line.indexOf(',');
        int memoryIndex =
            Integer.parseInt(line.substring(colonIndex + 1, commaIndex).trim());
        String pdu = bufReader.readLine();

        if (Message.isUserMessage(pdu)) {
          InMessage inMessage = new InMessage(memoryIndex, pdu);

          if (inMessage.getMpRefNo() == 0) {
            messageList.add(inMessage);
          }
          else {
            if (inMessage.getMpSeqNo() == 1) {
              if (mpMessage == null) {
                mpMessage = new InMessage(memoryIndex, pdu);
                mpMessage.setMpMemoryIndex(memoryIndex);
              }
            }
            else if (inMessage.getMpRefNo() == mpMessage.getMpRefNo()
                     && inMessage.getMpSeqNo() == mpMessage.getMpSeqNo() + 1) {
              mpMessage.addText(inMessage.getText());
              mpMessage.setMpSeqNo(inMessage.getMpSeqNo());
              mpMessage.setMpMemoryIndex(memoryIndex);
              if (mpMessage.getMpSeqNo() == mpMessage.getMpMaxNo()) {
                mpMessage.setMemoryIndex(-1);
                messageList.add(mpMessage);
              }
            }
          }
        }
        else if (Message.isStatusMessage(pdu)) {
          // TODO
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      try {
        bufReader.close();
      } catch (Exception e) {
      }
      strReader.close();
    }
    InMessage[] inMessages = new InMessage[messageList.size()];
    messageList.toArray(inMessages);
    return inMessages;
  }

  public boolean deleteMessage(int memoryIndex) {
    mSerialPort.send(COMMAND_DELETE_MESSAGE.replace(REPLACE_TOKEN, String
        .valueOf(memoryIndex)));
    return (mSerialPort.getResponse().matches(RESPONSE_OK));
  }

  public void deleteMessage(InMessage inMessage) {
    int memoryIndex = inMessage.getMemoryIndex();
    if (memoryIndex > 0) {
      deleteMessage(inMessage.getMemoryIndex());
    }
    else {
      if (memoryIndex == -1 && inMessage.getMpMemoryIndex().length() != 0) {
        StringTokenizer tokens =
            new StringTokenizer(inMessage.getMpMemoryIndex(), ",");
        while (tokens.hasMoreTokens()) {
          deleteMessage(Integer.parseInt(tokens.nextToken()));
        }
      }
    }
  }

  public void deleteMessages(InMessage[] inMessages) {
    if (inMessages != null && inMessages.length > 0) {
      for (int i = 0; i < inMessages.length; i++) {
        deleteMessage(inMessages[i]);
      }
    }
  }

  public void shutdown() {
    if (mSerialPort != null) {
      mSerialPort.close();
    }
  }

  public void finalize() {
    shutdown();
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer(200);
    try {
      buffer.append("Device: ").append(JVM.SystemProperty.NEW_LINE);
      buffer.append("   Manufacturer       : " + getManufacturer()).append(
          JVM.SystemProperty.NEW_LINE);
      buffer.append("   Model              : " + getModel()).append(
          JVM.SystemProperty.NEW_LINE);
      buffer.append("   Serial Number      : " + getSerialNo()).append(
          JVM.SystemProperty.NEW_LINE);
      buffer.append("   Software Version   : " + getSoftwareVersion()).append(
          JVM.SystemProperty.NEW_LINE);
      buffer.append("   IMSI               : " + getImsi()).append(
          JVM.SystemProperty.NEW_LINE);
      buffer.append("   Signal Level       : " + getSignalLevel() + "%")
          .append(JVM.SystemProperty.NEW_LINE);
      buffer.append("   Battery Level      : " + getBatteryLevel() + "%")
          .append(JVM.SystemProperty.NEW_LINE);
    } catch (RuntimeException e) {
      return "";
    }
    return buffer.toString();
  }

}
