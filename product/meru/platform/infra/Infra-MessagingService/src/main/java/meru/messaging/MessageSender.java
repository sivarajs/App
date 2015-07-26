package meru.messaging;

import java.io.Serializable;

public interface MessageSender {

  public Object send(String subject,
                   Serializable object);

  public Object send(String subject,
                   String data,
                   String type);

}
