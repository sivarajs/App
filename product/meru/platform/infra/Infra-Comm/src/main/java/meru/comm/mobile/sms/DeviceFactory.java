package meru.comm.mobile.sms;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class DeviceFactory {

  public static Device getDevice() {
    
    String port = null;
    int baudRate = 0;
    String pin = "0000";
    String smsc = "";
    String manufacturer = null;
    try {
      Properties properties = System.getProperties();
      port = properties.getProperty(PropertyConstants.MOBILE_PORT);
      baudRate = Integer.parseInt(properties.getProperty(PropertyConstants.MOBILE_BAUD_RATE));
      pin = properties.getProperty(PropertyConstants.MOBILE_PIN);
      smsc = properties.getProperty(PropertyConstants.MOBILE_SMSC_NUMBER);
      manufacturer = properties.getProperty(PropertyConstants.MOBILE_MANUFACTURER);
    } catch (Exception e) {
//      ConfigReader reader = ConfigReader.getInstance();
//      port = reader.getProperty("port");
//      baudRate = Integer.parseInt(reader.getProperty("baud_rate"));
//      manufacturer = reader.getProperty("manufacturer");
    }
    
    SMSSerialPort serialPort = null;
    try {
      serialPort = new SMSSerialPort(port,baudRate);
      
      Class<?> deviceClass = Class.forName(DeviceFactory.class.getPackage().getName()+".device.Device"+manufacturer);
      Constructor<?> constructor = deviceClass.getConstructor(new Class[] {SMSSerialPort.class, String.class, String.class});
      return (Device)constructor.newInstance(new Object[]{serialPort,pin,smsc});
    } catch (Throwable e) {
      if (serialPort != null) {
        serialPort.close();
      }
      if (e instanceof InvocationTargetException) {
        throw new RuntimeException(((InvocationTargetException)e).getTargetException().getMessage());
      }
      throw new RuntimeException(e);
    } 
  }
}
