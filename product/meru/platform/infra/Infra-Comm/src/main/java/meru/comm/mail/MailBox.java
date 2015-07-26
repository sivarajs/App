package meru.comm.mail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import meru.sys.IOSystem;
import meru.sys.JVM;

public class MailBox {
    private String mInboundHost;
    // private int mInboundPort;
    private String mUser;
    private String mEmail;
    private String mPassword;
    private String mInboundProtocal;
    private Properties mProperties;
    private AuthenticatorImpl mAuthenticator;
    private File mDownloadDir;

    public MailBox(Properties properties) {

        mProperties = properties;
        // Security.addProvider(new Provider());

        mUser = properties.getProperty("mail.user.name");
        mEmail = properties.getProperty("mail.user.email");
        mPassword = properties.getProperty("mail.user.password");

        mUser = mUser + "<" + mEmail + ">";

        properties.setProperty("user.email",
                               mEmail);
        properties.setProperty("user.password",
                               mPassword);

        mInboundProtocal = properties.getProperty("inbound.protocol");

        if (mInboundProtocal != null) {
            if (mInboundProtocal.equals("pop3")) {
                mInboundHost = properties.getProperty("pop3.host");
                // mInboundPort =
                // Integer.parseInt(properties.getProperty("mail.pop3.port"));
            }
        }

        properties.put("mail.smtp.socketFactory.class",
                       "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback",
                       "false");
        properties.put("mail.pop3.socketFactory.class",
                       "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.pop3.socketFactory.fallback",
                       "false");

        
        mAuthenticator = new AuthenticatorImpl(mEmail,
                                               mPassword);

        String downloadDir = properties.getProperty("download.dir");
        if (downloadDir != null && downloadDir.trim()
                                              .length() > 0) {
            mDownloadDir = new File(downloadDir);
            if (!mDownloadDir.exists())
                mDownloadDir.mkdirs();
        }
    }

    public void drop(MailEnvelop envelop) {
        Session session = Session.getDefaultInstance(mProperties,
                                                     mAuthenticator);

        MimeMessage mimeMessage = new MimeMessage(session);

        try {

            String from = envelop.getFrom();
            if (from == null) {
                from = mUser;
            }
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setRecipients(Message.RecipientType.TO,
                                      InternetAddress.parse(envelop.getTos(),
                                                            false));

            if (envelop.getCcs() != null) {
                mimeMessage.setRecipients(Message.RecipientType.CC,
                                          InternetAddress.parse(envelop.getCcs(),
                                                                false));

            }

            if (envelop.getBccs() != null) {
                mimeMessage.setRecipients(Message.RecipientType.BCC,
                                          InternetAddress.parse(envelop.getBccs(),
                                                                false));

            }

            mimeMessage.setSubject(envelop.getSubject());

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            if (envelop.getMessage() != null) {
                if (envelop.getContentType() != null) {
                    mimeBodyPart.setContent(envelop.getMessage(),
                                            envelop.getContentType());
                }
                else {
                    mimeBodyPart.setText(envelop.getMessage());
                }
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);

                mimeMessage.setContent(multipart);
            }

            mimeMessage.setSentDate(new Date());

            Transport.send(mimeMessage);
        } catch (Exception exception) {
            String message = "Unable to deliver the message [" + envelop + "]"
                    + JVM.SystemProperty.NEW_LINE + exception.getMessage();
            throw new DeliveryFailedException(message,
                                              exception);
        }
    }

    public MailEnvelop pickLatest() throws Exception {
        Session session = Session.getDefaultInstance(mProperties,
                                                     mAuthenticator);

        Store store = null;
        Folder folder = null;
        try {
            store = session.getStore(mInboundProtocal);
            store.connect(mInboundHost,
                          mEmail,
                          mPassword);

            folder = store.getFolder("INBOX");

            folder.open(1);

            int count = folder.getMessageCount();

            if (count <= 0) {
                return null;
            }

            Message message = folder.getMessage(count);

            MailEnvelop envelop = createEnvelop(message);

            return envelop;
        } finally {
            if (folder != null)
                try {
                    folder.close(false);
                } catch (Exception localException4) {
                }
            if (store != null)
                try {
                    store.close();
                } catch (Exception localException5) {
                }
        }
    }

    public List<MailEnvelop> pick(int start,
                                  int end) throws Exception {
        Session session = Session.getDefaultInstance(mProperties,
                                                     mAuthenticator);

        Store store = session.getStore(mInboundProtocal);

        store.connect(mInboundHost,
                      mEmail,
                      mPassword);

        Folder folder = store.getFolder("INBOX");

        folder.open(1);
        System.out.println("$$$$$$$$$$$$ UnRead "
                + folder.getUnreadMessageCount());
        Message[] messages = folder.getMessages(start,
                                                end);
        List<MailEnvelop> mails = null;
        if (messages != null) {
            mails = new ArrayList<MailEnvelop>(messages.length);
            for (Message message : messages) {
                Address[] addresses = message.getFrom();
                String from = null;
                if ((addresses != null) && (addresses.length > 0)) {
                    from = addresses[0].toString();
                }
                String tos = "";
                addresses = message.getAllRecipients();
                if ((addresses != null) && (addresses.length > 0)) {
                    int i = 0;
                    for (Address address : addresses) {
                        if (i++ > 0) {
                            tos += ",";
                        }
                        tos += address.toString();
                    }
                }

                MailEnvelop envelop = new MailEnvelop(from,
                                                      tos,
                                                      message.getSubject(),
                                                      null);

                Multipart multipart = (Multipart) message.getContent();
                int l = 0;
                for (int count = multipart.getCount(); l < count; ++l) {
                    Object part = multipart.getBodyPart(l);

                    String disposition = ((Part) part).getDisposition();

                    if (disposition == null) {
                        envelop.setMessage(((Part) part).getContent()
                                                        .toString());
                    }
                    else if (disposition.equals("inline")) {
                        envelop.setMessage(IOSystem.read(((Part) part).getInputStream()));
                    }
                    else if (disposition.equals("attachment")) {
                        envelop.addAttachment(saveAttachment((Part) part));
                    }
                }

                mails.add(envelop);
            }
        }
        return (List<MailEnvelop>) mails;
    }

    private MailEnvelop createEnvelop(Message message) throws Exception {
        Address[] addresses = message.getFrom();
        String from = null;
        if (addresses != null && addresses.length > 0) {
            from = addresses[0].toString();
        }
        
        String tos = "";
        addresses = message.getAllRecipients();
        if (addresses != null && addresses.length > 0) {
            int i = 0;
            for (Address address : addresses) {
                if (i++ > 0) {
                    tos +=",";     
                }
                tos += address.toString();
            }
        }

        MailEnvelop envelop = new MailEnvelop(from,
                                              tos,
                                              message.getSubject(),
                                              null);

        if (message.getReceivedDate() == null) {
            envelop.setDate(message.getReceivedDate());
        }
        else {
            envelop.setDate(message.getSentDate());
        }

        Multipart multipart = (Multipart) message.getContent();
        int j = 0;
        for (int count = multipart.getCount(); j < count; ++j) {
            Object part = multipart.getBodyPart(j);

            String disposition = ((Part) part).getDisposition();

            if (disposition == null) {
                envelop.setMessage(((Part) part).getContent()
                                                .toString());
            }
            else if (disposition.equals("inline")) {
                envelop.setMessage(IOSystem.read(((Part) part).getInputStream()));
            }
            else if (disposition.equals("attachment")) {
                envelop.addAttachment(saveAttachment((Part) part));
            }
        }

        return (MailEnvelop) envelop;
    }

    private File saveAttachment(Part part) throws Exception {
        String fileName = part.getFileName();
        InputStream inputStream = part.getInputStream();

        File file = new File(mDownloadDir,
                             fileName);
        FileOutputStream outputStream = new FileOutputStream(file);

        IOSystem.transfer(inputStream,
                            outputStream);

        return file;
    }

    private class AuthenticatorImpl extends Authenticator {
        private String mUserName;
        private String mPassword;

        public AuthenticatorImpl(String userName,
                                 String password) {
            mUserName = userName;
            mPassword = password;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(mUserName,
                                              mPassword);
        }
    }
}