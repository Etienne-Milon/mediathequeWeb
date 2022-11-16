package fr.cuib.mediathequeweb.controller;

import fr.cuib.mediathequeweb.dao.CompteDAO;
import fr.cuib.mediathequeweb.dao.DaoFactory;
import fr.cuib.mediathequeweb.metier.Compte;
import fr.cuib.mediathequeweb.metier.SHA256;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Named("beanCompte")
@SessionScoped
public class BeanCompte implements Serializable {

    private String login;
    private String password;
    private Compte compte;


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

    public void doLogin() throws NoSuchAlgorithmException, NoSuchProviderException {
        FacesMessage message = null;
        boolean loggedIn = false;
        if (login != null && DaoFactory.getCompteDAO().checkAccountExistence(Integer.parseInt(login))){
            if (password != null) {
                System.out.println("password non null");
                SHA256 sha256 = new SHA256(password);
                if (DaoFactory.getCompteDAO().validatePwd(login, sha256.hash)) {
                    loggedIn = true;
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenue", login);
                    //TODO faire apparaitre le profil dans le menu
                } else {
                    System.out.println(sha256.hash);
                    loggedIn = false;
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Echec Log in", "Identifiant ou password non reconnus");
                }
            } else {
                loggedIn = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Echec Log in", "Password manquant");
            }
        }
        else{
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Echec Log in", "identifiant non reconnu");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("form:messages", "form:loginDialog");

    }
}

