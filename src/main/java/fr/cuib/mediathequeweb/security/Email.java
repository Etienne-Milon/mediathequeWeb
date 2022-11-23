package fr.cuib.mediathequeweb.security;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.Properties;

public class Email {
    public static void sendEmail(String toEmail,String subject, String body){

        final String fromEmail = "81a7f647f039c6";
        final String password = "e833d6a56d482d";

        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.mailtrap.io");
        props.put("mail.smtp.port","2525");

        Session mail = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(fromEmail,password);
            }
        }
        );
        try {
            Message message = new MimeMessage(mail);
            message.setFrom(new InternetAddress("authentification@CUIB.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Ouai test test 1, 2");
            message.setText("Bonjour, cliquer sur le lien pour finaliser l'inscription"
                + new URI("http://localhost:8080/CUIB/faces/index.xhtml"));
        } catch (MessagingException e) {
        System.out.println("problème durant l'envoi");
        throw new RuntimeException(e);
    } catch (URISyntaxException e) {
        throw new RuntimeException(e);
    }
    }
}
