package fr.cuib.mediathequeweb.controller;

import fr.cuib.mediathequeweb.dao.DaoFactory;
import fr.cuib.mediathequeweb.metier.Compte;
import fr.cuib.mediathequeweb.metier.Utilisateur;
import fr.cuib.mediathequeweb.security.ApplicationBean;

import fr.cuib.mediathequeweb.security.Email;
import fr.cuib.mediathequeweb.security.SecurityTools;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Named
@RequestScoped
public class UserBean implements Serializable {
    private Compte compte = new Compte();
    private Utilisateur utilisateur = new Utilisateur();
    ApplicationBean applicationBean;

    @PostConstruct
    public void initialize(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String param = request.getParameter("compte");
    }

    public ApplicationBean getApplicationBean() {
        return applicationBean;
    }

    public void setApplicationBean(ApplicationBean applicationBean) {
        this.applicationBean = applicationBean;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void creer() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        applicationBean = new ApplicationBean();
        applicationBean.initialize();
        applicationBean.setPbkdf2PasswordHash(new Pbkdf2PasswordHashImpl());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,10);
        Date expiration = calendar.getTime();
        String url = String.format("/l=%s&c=%s&e=%s&p=%s&d=%s",
                utilisateur.getNom(),
                SecurityTools.checksum(utilisateur.getNom() + compte.getEmail()),
                compte.getEmail(),
                applicationBean.passwordHash(compte.getPassword()),
                new SimpleDateFormat("dd-MM-yy-HH:mm:ss").format(expiration));
        String urlEncode = SecurityTools.encrypt(url);
        String absoluteURL = applicationBean.getAbsolutePath() + "/confirm.jsf?compte=" + urlEncode;
//        String link = "<a href=\"" + valideUrl + "\">CONFIRMER</a>";
        String body =
                "<div style=\"display: flex; flex-direction: column; justify-content: center; align-items: center;\">" +
                        "<h2>Finaliser la création de votre compte</h2>" +
                        "<p>Pour crée votre compte, veuillez cliquer sur le bouton ci-dessous</p>" +
                        "<a href=\"" + absoluteURL + "\">" +
                            "<button style=\"background-color: #506d90; color: white; padding: 5px;\">CONFIRMER</button>" +
                        "</a>" +
                "</div>";
        Email.sendEmail(compte.getEmail(),"Confirmation",body);

    }

    public void validerCreation() throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String fullURL = request.getHeader("referer");
        String paramEncodedURL = fullURL.split("compte=")[1];
        String paramDecodedURL = SecurityTools.decrypt(paramEncodedURL);
        Map<String,String> mapping = buildQueryMap(paramDecodedURL);

        compte.setEmail(mapping.get("e"));
        compte.setPassword(mapping.get("p"));
        DaoFactory.getCompteDAO().insert(compte);
        System.out.println("Le compte a bien été créé");
    }

    private Map<String, String> buildQueryMap(String query) {
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