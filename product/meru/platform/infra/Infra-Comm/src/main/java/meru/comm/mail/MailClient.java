package meru.comm.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//import com.sun.net.ssl.internal.ssl.Provider;

public class MailClient {
    private Properties mProperties;
    private String mUserName;
    private AuthenticatorImpl mAuthenticator;

    public MailClient(Properties properties) throws Exception {
        this.mProperties = properties;
        this.mUserName = this.mProperties.getProperty("mail.smtp.user");
        String password = this.mProperties.getProperty("mail.smtp.password");
        // Security.addProvider(new Provider());
        this.mAuthenticator = new AuthenticatorImpl(this.mUserName,
                                                    password);
    }

    public Session getSession() {
        return Session.getDefaultInstance(this.mProperties,
                                          this.mAuthenticator);
    }

    public void send(String to,
                     String subject,
                     String messageBody) throws Exception {
        Session session = Session.getDefaultInstance(this.mProperties,
                                                     this.mAuthenticator);

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(this.mUserName));
        mimeMessage.setRecipients(Message.RecipientType.TO,
                                  to);
        mimeMessage.setSubject(subject);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setText(messageBody);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        mimeMessage.setContent(multipart);
        mimeMessage.setSentDate(new Date());
        Transport.send(mimeMessage);
    }

    private class AuthenticatorImpl extends Authenticator {
        private String mPassword;

        public AuthenticatorImpl(String userName,
                                 String password) {
            MailClient.this.mUserName = userName;
            this.mPassword = password;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(MailClient.this.mUserName,
                                              this.mPassword);
        }
    }
}