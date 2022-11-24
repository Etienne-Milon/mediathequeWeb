package fr.cuib.mediathequeweb.controller;


import fr.cuib.mediathequeweb.dao.DaoFactory;
import fr.cuib.mediathequeweb.metier.Compte;

import fr.cuib.mediathequeweb.security.ApplicationBean;
import fr.cuib.mediathequeweb.security.SecurityTools;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import org.primefaces.PrimeFaces;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

@Named
@SessionScoped
public class ConfirmationBean implements Serializable {

    private String login;
    private String password;
    private Compte compte;

    @PostConstruct
    public void init(){
        compte = new Compte();
    }


    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(){
        FacesMessage message = null;
        ApplicationBean ab = new ApplicationBean();
        ab.setPbkdf2PasswordHash(new Pbkdf2PasswordHashImpl());
        Compte cpt = DaoFactory.getCompteDAO().getById(Integer.parseInt(login));
        if(ab.passwordVerify(password, cpt.getPassword())){
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenue", login);
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Echec Log in", "abc");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("form:messages", "form:loginDialog");
    }

    public void register() throws SQLException {
        FacesMessage message = null;
        ApplicationBean ab = new ApplicationBean();
        ab.setPbkdf2PasswordHash(new Pbkdf2PasswordHashImpl());
        String passwordHash = ab.passwordHash(password);

        if(DaoFactory.getCompteDAO().checkAccountExistenceByMail(compte.getEmail())){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Compte déjà existant", "");
        }else{
            DaoFactory.getCompteDAO().insert(compte, passwordHash);
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "En attente de confirmation", "Un lien vient de vous être envoyer par mail, veuillez le confirmation afin de valider la création de votre compte");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("form:messages", "form:register");
    }

    public void redirect(String url){
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void recuperer() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ApplicationBean ab = new ApplicationBean();
        ab.setPbkdf2PasswordHash(new Pbkdf2PasswordHashImpl());
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String url = params.get("compte");
        String urlDecode = SecurityTools.decrypt(url);
        String Usrlogin = String.valueOf(url.split("")[0]);
        String Usrpsw = String.valueOf(url.split("")[1]);
        SecurityTools.checksum(Usrlogin+Usrpsw );

    }
}

