package meru.comm.mobile.sms.device;

import meru.comm.mobile.sms.Device;
import meru.comm.mobile.sms.OutMessage;
import meru.comm.mobile.sms.SMSSerialPort;

public class DeviceNokia extends Device {

  public DeviceNokia(String port, int baudRate) {

    super(port, baudRate, "0000", "");
  }


  public DeviceNokia(SMSSerialPort serialPort) {

    super(serialPort, "0000", "");
  }


  public DeviceNokia(String port, int baudRate, String pin) {

    super(port, baudRate, pin, "");
  }


  public DeviceNokia(String port, int baudRate, String pin, String smscNumber) {

    super(new SMSSerialPort(port, baudRate), pin, smscNumber);
  }


  public DeviceNokia(SMSSerialPort serialPort, String pin, String smscNumber) {

    super(serialPort, pin, smscNumber);
  }
  
  public static void main(String[] args) {
    DeviceNokia nokia = new DeviceNokia("COM5",9600);
    try {
      OutMessage message = new OutMessage("+919880960654","Good Night");
      nokia.sendMessage(message);
    } finally {
      nokia.shutdown();
    }
  }
}
