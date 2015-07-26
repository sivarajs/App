package meru.comm.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import meru.sys.JVM;

public class MailEnvelop {

    private String mFrom;
    private String mTos;
    private String mCCs;
    private String mBCCs;
    private String mSubject;
    private String mMessage;
    private Date mDate;
    private List<File> mAttachmentList;
    private String mContentType;

    public MailEnvelop(String to,
                       String subject,
                       String message) {

        this(null,
             to,
             subject,
             message);
    }

    public MailEnvelop(String from,
                       String tos,
                       String subject,
                       String message) {

        this.mFrom = from;

        if (tos == null || tos.length() == 0) {
            throw new RuntimeException("To ids are missing");
        }

        this.mTos = tos;

        this.mSubject = subject;

        this.mMessage = message;
    }

    public void setContentType(String contentType) {

        this.mContentType = contentType;
    }

    public String getContentType() {

        return this.mContentType;
    }

    public void setDate(Date date) {

        this.mDate = date;
    }

    public Date getDate() {

        return this.mDate;
    }

    public String getFrom() {

        return this.mFrom;
    }

    public String getTos() {

        return this.mTos;
    }

    public void setCCs(String cc) {

        if (cc == null) {
            return;
        }

        mCCs = cc;
    }

    public String getCcs() {

        return this.mCCs;
    }

    public void setBCCs(String bcc) {

        if (bcc == null) {
            return;
        }

        mBCCs = bcc;
    }

    public String getBccs() {
        return mBCCs;
    }

    public String getSubject() {

        return this.mSubject;
    }

    public void setMessage(String message) {

        this.mMessage = message;
    }

    public String getMessage() {

        return this.mMessage;
    }

    public void setAttachments(List<File> fileList) {

        this.mAttachmentList = fileList;
    }

    public List<File> getAttachments() {

        return this.mAttachmentList;
    }

    public void addAttachment(File file) {

        if (this.mAttachmentList == null) {
            this.mAttachmentList = new ArrayList<File>();
        }

        this.mAttachmentList.add(file);
    }

    public String toString() {

        String str = JVM.SystemProperty.NEW_LINE + "From = " + this.mFrom
                + JVM.SystemProperty.NEW_LINE + "TO = " + this.mTos
                + JVM.SystemProperty.NEW_LINE;

        if (this.mCCs != null) {
            str = str + "CC = " + this.mCCs + JVM.SystemProperty.NEW_LINE;
        }

        str = str + "Subject = " + this.mSubject + JVM.SystemProperty.NEW_LINE;

        if (this.mMessage != null) {
            str = str + "Message = " + this.mMessage;
        }

        return str;
    }
}