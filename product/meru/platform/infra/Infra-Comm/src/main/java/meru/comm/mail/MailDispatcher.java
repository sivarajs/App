package meru.comm.mail;

import java.util.List;

public interface MailDispatcher {

  public abstract void dispatch(MailEnvelop paramMailEnvelop)
      throws DeliveryFailedException;


  public abstract void dispatch(List<MailEnvelop> paramList)
      throws DeliveryFailedException;
}