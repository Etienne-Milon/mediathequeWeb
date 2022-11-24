package fr.cuib.mediathequeweb.controller;

import fr.cuib.mediathequeweb.security.ApplicationBean;

import fr.cuib.mediathequeweb.security.Email;
import fr.cuib.mediathequeweb.security.SecurityTools;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Named
@RequestScoped
public class UserBean implements Serializable {

    public ApplicationBean getApplicationBean() {
        return applicationBean;
    }

    public void setApplicationBean(ApplicationBean applicationBean) {
        this.applicationBean = applicationBean;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    String login;
    String email;
    String password;
    ApplicationBean applicationBean;


    public void Creer() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        applicationBean = new ApplicationBean();
        applicationBean.initialize();
        applicationBean.setPbkdf2PasswordHash(new Pbkdf2PasswordHashImpl());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,10);
        Date expiration = calendar.getTime();
        String url = String.format("/l=%s&c=%s&e=%s&p=%s&d=%s",
                login,
                SecurityTools.checksum(login+email),
                email,
                applicationBean.passwordHash(password),
                new SimpleDateFormat("dd-MM-yy-HH:mm:ss").format(expiration));
        String urlEncode = SecurityTools.encrypt(url);
        String valideUrl = applicationBean.getAbsolutePath() + "/confirm.jsf?compte=" + urlEncode;
        Email.sendEmail(email,"Confirmation",valideUrl);
    }

    public void recuperer() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ApplicationBean ab = new ApplicationBean();
        ab.setPbkdf2PasswordHash(new Pbkdf2PasswordHashImpl());
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String url = params.get("compte");
        String urlDecoded = SecurityTools.decrypt(url);
        Map<String,String> mapping = buildQueryMap(urlDecoded);
    }

    private static Map<String, String> buildQueryMap(String query) {
        if (query == null)
            return null;
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String[] currentParam = param.split("=");
            if (currentParam.length != 2) {
                String name = currentParam[0];
                String value = query.substring(query.indexOf("p=")+2,query.indexOf("=&")+1);
                map.put(name, value);
                continue;
            }
            String name = currentParam[0];
            String value = currentParam[1];
            map.put(name, value);
        }
        return map;
    }

}