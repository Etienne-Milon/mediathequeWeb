package fr.cuib.mediathequeweb.security;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class Email {
    public static void sendEmail(String toEmail, String subject, String body){
        //provide sender's email ID
        String from = "service@cuib.fr";
        //provide Mailtrap's username
        final String username = "5d31380e4541f3";
        //provide Mailtrap's password
        final String password = "42270df7d6e3c5";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mailtrap.io");
        props.put("mail.smtp.socketFactory.port", "2525");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "2525");
//        props.put("mail.debug", "true");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            //set email subject field
            message.setSubject(subject);
            //set the content of the email message
            message.setText(body);
            //send the email message
            Transport.send(message);
            System.out.println("Email envoyé");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
