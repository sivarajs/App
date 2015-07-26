package meru.comm.mail;

import java.util.ArrayList;
import java.util.List;

public class MailDispatcherImpl implements MailDispatcher {
  private MailBox mMailBox;

  public MailDispatcherImpl(MailBox mailBox) {
    this.mMailBox = mailBox;
  }

  public void dispatch(MailEnvelop envelop) {
    List<MailEnvelop> envelops = new ArrayList<MailEnvelop>(1);
    envelops.add(envelop);
    dispatch(envelops);
  }

  public void dispatch(List<MailEnvelop> envelops) {
    for (MailEnvelop envelop : envelops)
      this.mMailBox.drop(envelop);
  }
}