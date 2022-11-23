package fr.cuib.mediathequeweb.controller;

import fr.cuib.mediathequeweb.security.ApplicationBean;

import fr.cuib.mediathequeweb.security.Email;
import fr.cuib.mediathequeweb.security.SecurityTools;
import jakarta.inject.Inject;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserBean implements Serializable {

    @Inject
    ApplicationBean applicationBean;
    String login;
    String email;
    String password;


    public void Creer() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,10);
        Date expiration = calendar.getTime();
        String url = String.format("/%s?c=%s&e=%s&p=%s&d=%s",
                login,
                SecurityTools.checksum(login+email),
                email,
                applicationBean.passwordHash(password),
                new SimpleDateFormat("dd-MM-yy-HH:mm:ss").format(expiration));
        String urlEncode = SecurityTools.encrypt(url);
        String valideUrl = applicationBean.getAbsolutePath() + "/confirm.jsf?compte=" + urlEncode;
        StringBuilder body = new StringBuilder("Veuillez cliquer le lien");
        body.append(valideUrl);
        Email.sendEmail(email,"Confirmation",body.toString());


    }

}