package meru.comm.mobile.sms.device;

import meru.comm.mobile.sms.Device;
import meru.comm.mobile.sms.SMSSerialPort;

public class DeviceSonyEricsson extends Device {

  private static final String RESPONSE_RESET_SUPPORTED = "\\+CFUN: \\([^)]+\\),\\([^)]+\\)";
  private static final String RESPONSE_CNMI_2 = "+CNMI: (2)";
  private static final String RESPONSE_CNMI_3 = "+CNMI: (3)";

  private static final String COMMAND_RESET = "AT+CFUN=1,1\r";

  private static final String COMMAND_CNMI = "AT+CNMI=?\r";
  private static final String COMMAND_DISABLE_INDICATIONS_2 = "AT+CNMI=2,0,0,0\r";
  private static final String COMMAND_DISABLE_INDICATIONS_3 = "AT+CNMI=3,0,0,0\r";


  public DeviceSonyEricsson(String port, int baudRate) {

    super(port, baudRate, "0000", "");
  }


  public DeviceSonyEricsson(SMSSerialPort serialPort) {

    super(serialPort, "0000", "");
  }


  public DeviceSonyEricsson(String port, int baudRate, String pin) {

    super(port, baudRate, pin, "");
  }


  public DeviceSonyEricsson(String port, int baudRate, String pin,
                            String smscNumber) {

    super(new SMSSerialPort(port, baudRate), pin, smscNumber);
  }


  public DeviceSonyEricsson(SMSSerialPort serialPort, String pin,
                            String smscNumber) {

    super(serialPort, pin, smscNumber);
  }


  public void sendAdditionalChars() {

    mSerialPort.send((char) 13);
  }


  public void reset() {

    mSerialPort.send(COMMAND_RESET_SUPPORTED);
    String response = mSerialPort.getResponse();
    if (response != null
        && response.indexOf(RESPONSE_RESET_NOT_SUPPORTED) == -1) {
      if (response.matches(RESPONSE_RESET_SUPPORTED)) {
        mSerialPort.send(DeviceSonyEricsson.COMMAND_RESET);
      }
      else {
        mSerialPort.send(Device.COMMAND_RESET);
      }

      try {
        Thread.sleep(10000);
      } catch (Exception e) {
      }
      mSerialPort.clear();
    }
  }


  public boolean disableIndications() {

    mSerialPort.send(COMMAND_CNMI);
    String response = mSerialPort.getResponse();
    if (response == null) {
      return false;
    }
    if (response.toUpperCase().indexOf(RESPONSE_CNMI_2) >= 0) {
      mSerialPort.send(COMMAND_DISABLE_INDICATIONS_2);
    }
    else if (response.toUpperCase().indexOf(RESPONSE_CNMI_3) >= 0) {
      mSerialPort.send(COMMAND_DISABLE_INDICATIONS_3);
    }
    else {
      return false;
    }
    return (mSerialPort.getResponse().matches(RESPONSE_OK));
  }
}
