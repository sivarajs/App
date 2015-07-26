package meru.comm.mobile.sms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;


public class SMSSerialPort implements SerialPortEventListener {

  private static final String APP_NAME = "SMSEngine";
  private static final int PORT_OPEN_TIMEOUT = 15000;
  private static final int DEVICE_RESPONSE_TIMEOUT = 25000;
  private static final int MESSAGE_BUFFER_SIZE = 1000;

  private String mPort;
  private int mBaudRate;

  private SerialPort mSerialPort;
  private InputStream mInputStream;
  private OutputStream mOutputStream;


  public SMSSerialPort(String port, int baudRate) {

    mPort = port;
    mBaudRate = baudRate;
    init();
  }


  private void init() {

    CommPortIdentifier portIdentifier = null;
    
    try {
      portIdentifier = CommPortIdentifier.getPortIdentifier(mPort);
      mSerialPort =
          (SerialPort) portIdentifier.open(APP_NAME, PORT_OPEN_TIMEOUT);
      mSerialPort.setSerialPortParams(mBaudRate,
                                      SerialPort.DATABITS_8,
                                      SerialPort.STOPBITS_1,
                                      SerialPort.PARITY_NONE);

      mSerialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);

      mSerialPort.addEventListener(this);

      mSerialPort.notifyOnDataAvailable(true);
      mSerialPort.notifyOnOutputEmpty(true);
      mSerialPort.notifyOnBreakInterrupt(true);
      mSerialPort.notifyOnFramingError(true);
      mSerialPort.notifyOnOverrunError(true);
      mSerialPort.notifyOnParityError(true);

      mSerialPort.setInputBufferSize(MESSAGE_BUFFER_SIZE);
      mSerialPort.setOutputBufferSize(MESSAGE_BUFFER_SIZE);
      mSerialPort.enableReceiveTimeout(DEVICE_RESPONSE_TIMEOUT);

      mInputStream = mSerialPort.getInputStream();
      mOutputStream = mSerialPort.getOutputStream();
    } catch (NoSuchPortException e) {
      close();
      throw new RuntimeException("The serial port '" + mPort + "' is not found");
    } catch (PortInUseException e) {
      close();
      throw new RuntimeException("The serial port '" + mPort
          + "' is already in use or \n the device is not connected to the port");
    } catch (Throwable e) {
      close();
      throw new RuntimeException(e);
    }
  }


  public void close() {

    if (mSerialPort != null) {
      try {
        mSerialPort.close();
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
      mSerialPort = null;
    }
  }


  public void send(String message) {

    if (mSerialPort == null) {
      throw new RuntimeException("The port has been closed");
    }
    try {
      int length = message.length();
      for (int i = 0; i < length; i++) {
        mOutputStream.write((byte) message.charAt(i));
      }
      mOutputStream.flush();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  public void send(char c) {

    try {
      mOutputStream.write((byte) c);
      mOutputStream.flush();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  public void clear() {

    try {
      while (mInputStream.available() > 0) {
        mInputStream.read();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public boolean dataAvailable() {

    try {
      return (mInputStream.available() > 0 ? true : false);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  public String getResponse() {

    int response;

    int maxRetries = 3;
    int retry = 0;
    StringBuffer buffer = new StringBuffer(MESSAGE_BUFFER_SIZE);

    while (retry < maxRetries) {
      try {
        while (true) {
          response = mInputStream.read();
          if (response == -1) {
            buffer.delete(0, buffer.length());
            break;
          }
          buffer.append((char) response);
          if (buffer.toString().matches("\\s*[\\p{ASCII}]*\\s+OK\\s"))
            break;
          if (buffer.toString().matches("\\s*[\\p{ASCII}]*\\s+ERROR\\s"))
            break;
          if (buffer.toString()
                    .matches("\\s*[\\p{ASCII}]*\\s+ERROR: \\d\\d\\d\\s"))
            break;
          if (buffer.toString().matches("\\s*[\\p{ASCII}]*\\s+SIM PIN\\s"))
            break;
        }
        retry = maxRetries;
      } catch (Exception e) {
        if (retry < maxRetries) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e1) {
            throw new RuntimeException("Could not read the response");
          }
          retry++;
        }
        else {
          throw new RuntimeException(e);
        }
      }
    }
    clear();
    return buffer.toString();
  }


  public void serialEvent(SerialPortEvent event) {

  }


}
