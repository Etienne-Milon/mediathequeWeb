package fr.cuib.mediathequeweb.security;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

public class Email {
    public static void sendEmail(String toEmail,String subject, String body){
        final String fromEmail = "81a7f647f039c6";
        final String password = "e833d6a56d482d";

        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.mailtrap.io");

    }
}
