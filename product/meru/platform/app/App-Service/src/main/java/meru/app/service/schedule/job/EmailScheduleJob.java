package meru.app.service.schedule.job;

import java.util.List;

import meru.app.service.ServiceManager;
import meru.app.service.schedule.AppScheduleJob;
import meru.comm.mail.MailBox;
import meru.comm.mail.MailEnvelop;
import meru.messaging.MessageState;
import meru.persistence.EntityQuery;
import meru.sys.SystemCalendar;
import app.domain.comm.SendEmail;

public class EmailScheduleJob extends AppScheduleJob {

    @Override
    public void execute() {

        EntityQuery<SendEmail> entityQuery = appEngine.createQuery(SendEmail.class);
        entityQuery.addQueryParameter("state",
                                      MessageState.NEW.getState());

        List<SendEmail> emails = appEngine.get(entityQuery);
        MailBox mailBox = serviceManager.getService(ServiceManager.MAIL_BOX_NAME,
                                                    MailBox.class);
        if (emails != null) {
            for (SendEmail emailMsg : emails) {

                MailEnvelop envelop = new MailEnvelop(emailMsg.getTos(),
                                                      emailMsg.getSubject(),
                                                      emailMsg.getMessage());

                envelop.setCCs(emailMsg.getCcs());
                envelop.setBCCs(emailMsg.getBccs());
                envelop.setContentType("text/html");
                mailBox.drop(envelop);
                emailMsg.setDeliveredOn(SystemCalendar.getInstance()
                                                      .getUTCCalendar());
                emailMsg.setState(MessageState.DELIVERED.getState());

                appEngine.save(emailMsg);
            }
        }

    }
}
