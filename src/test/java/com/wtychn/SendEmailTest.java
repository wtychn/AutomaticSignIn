package com.wtychn;

import junit.framework.TestCase;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

public class SendEmailTest extends TestCase {

    public void testSend() throws MessagingException, GeneralSecurityException {
        SendEmail se = new SendEmail();
        se.send("Test Email", "Hello QQ Mail!");
    }
}