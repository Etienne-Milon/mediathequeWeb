package fr.cuib.mediathequeweb.metier;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Article {

    private Reference editeur;
    private ArrayList<Reference> genres;
    private ArrayList<Reference> langues;
    private ArrayList<Reference> mediatheques;
    private Reference format;
    private long EAN13;
    private String titre;

    private ArrayList<Piste> listePiste;
    private ArrayList<Exemplaire> listeExemplaire;
    private BigDecimal prixAchat;
    private Boolean grandeValeur;
    private Film film;
    private Livre livre;

    public Article(long EAN13, String titre, BigDecimal prixAchat, Boolean grandeValeur) {
        this.EAN13 = EAN13;
        this.titre = titre;
        this.prixAchat = prixAchat;
        this.grandeValeur = grandeValeur;
    }

    public Article(long EAN13, String titre) {
        this.EAN13 = EAN13;
        this.titre = titre;
    }

    public Article() {
    }

    public void setEAN13(long EAN13) {
        this.EAN13 = EAN13;
    }

    public long getEAN13() {
        return EAN13;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setEditeur(Reference editeur) {
        this.editeur = editeur;
    }

    public void setFormat(Reference format) {
        this.format = format;
    }

    public Reference getFormat() {
        return format;
    }

    public Reference getEditeur() {
        return editeur;
    }

    public ArrayList<Reference> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Reference> genres) {
        this.genres = genres;
    }

    public ArrayList<Piste> getListePiste() {
        return listePiste;
    }

    public void setListePiste(ArrayList<Piste> listePiste) {
        this.listePiste = listePiste;
    }

    public ArrayList<Reference> getMediatheques() {
        return mediatheques;
    }

    public void setExemplaire(ArrayList<Exemplaire> listeExemplaire) {
        this.listeExemplaire = listeExemplaire;
    }

    public ArrayList<Exemplaire> getListeExemplaire() {
        return listeExemplaire;
    }

    public void setMediatheque(ArrayList<Reference> mediatheques) {
        this.mediatheques = mediatheques;
    }

    public ArrayList<Reference> getLangues() {
        return langues;
    }

    public void setLangues(ArrayList<Reference> langues) {
        this.langues = langues;
    }

    public void addLangue(Langue langue) {
        this.langues = new ArrayList<>();
        langues.add(langue);
    }


    public BigDecimal getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(BigDecimal prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getGrandeValeur() {
        if (grandeValeur == false) {
            return 0;
        } else
            return 1;
    }

    public void setGrandeValeur(Boolean grandeValeur) {
        this.grandeValeur = grandeValeur;
    }

    public String getTitre() {
        return titre;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
}
