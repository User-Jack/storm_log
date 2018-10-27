package com.qf.logmonitor1704.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by liangdmaster on 2017/2/23.
 * 邮件认证
 */
public class MailAuthenticator extends Authenticator {
    String userName;
    String userPassword;

    public MailAuthenticator() {
        super();
    }

    public MailAuthenticator(String user, String pwd) {
        super();
        userName = user;
        userPassword = pwd;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, userPassword);
    }

}

