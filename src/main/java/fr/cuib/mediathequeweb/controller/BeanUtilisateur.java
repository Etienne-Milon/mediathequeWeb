package fr.cuib.mediathequeweb.controller;

import fr.cuib.mediathequeweb.metier.Utilisateur;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
@Named("beanUtilisateur")
@SessionScoped
public class BeanUtilisateur implements Serializable {
    private Utilisateur utilisateur;


    @PostConstruct
    public void init(){
        utilisateur = new Utilisateur();
    }


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
