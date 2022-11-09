package fr.cuib.mediathequeweb.controller;

import fr.cuib.mediathequeweb.dao.DaoFactory;
import fr.cuib.mediathequeweb.metier.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("bean")
@ViewScoped
public class Bean implements Serializable {

    private Article articleSelected;
    private Editeur editeur;
    private Etat etat;
    private Exemplaire exemplaire;
    private Film film;
    private Format format;
    private Genre genre;
    private Langue langue;
    private Livre livre;
    private Mediatheque mediatheque;
    private Personne personne;
    private Piste piste;
    private Serie serie;

    private static List<Article> allArticles;
    private static List<Editeur> allEditeurs;
    private static List<Reference> allEtats;
    private static List<Exemplaire> allExemplaires;
    private static List<Film> allFilms;
    private static List<Reference> allFormats;
    private static List<Reference> allGenres;
    private static List<Langue> allLangues;
    private static List<Livre> allLivres;
    private static List<Reference> allMediatheques;
    private static List<Personne> allPersonnes;
    private static List<Piste> allPistes;



    @PostConstruct
    private void init()
    {
        allArticles = DaoFactory.getArticleDAO().getAll();
        allEditeurs = DaoFactory.getEditeurDAO().getAll();
        allEtats = DaoFactory.getEtatDAO().getAll();
        allExemplaires = DaoFactory.getExemplaireDAO().getAll();
        allFilms = DaoFactory.getFilmDAO().getAll();
        allFormats = DaoFactory.getFormatDAO().getAll();
        allGenres = DaoFactory.getGenreDAO().getAll();
        allLangues = DaoFactory.getLangueDAO().getAll();
        allLivres = DaoFactory.getLivreDAO().getAll();
        allMediatheques = DaoFactory.getMediathequeDAO().getAll();
        allPersonnes = DaoFactory.getPersonneDAO().getAll();
        allPistes = DaoFactory.getPisteDAO().getAll();

    }

    public Article getArticleSelected() {
        return articleSelected;
    }

    public void setArticleSelected(Article articleSelected) {
        this.articleSelected = articleSelected;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Mediatheque getMediatheque() {
        return mediatheque;
    }

    public void setMediatheque(Mediatheque mediatheque) {
        this.mediatheque = mediatheque;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Piste getPiste() {
        return piste;
    }

    public void setPiste(Piste piste) {
        this.piste = piste;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public static List<Article> getAllArticles() {
        return allArticles;
    }

    public static void setAllArticles(List<Article> allArticles) {
        Bean.allArticles = allArticles;
    }

    public static List<Editeur> getAllEditeurs() {
        return allEditeurs;
    }

    public static void setAllEditeurs(List<Editeur> allEditeurs) {
        Bean.allEditeurs = allEditeurs;
    }

    public static List<Reference> getAllEtats() {
        return allEtats;
    }

    public static void setAllEtats(List<Reference> allEtats) {
        Bean.allEtats = allEtats;
    }

    public static List<Exemplaire> getAllExemplaires() {
        return allExemplaires;
    }

    public static void setAllExemplaires(List<Exemplaire> allExemplaires) {
        Bean.allExemplaires = allExemplaires;
    }

    public static List<Film> getAllFilms() {
        return allFilms;
    }

    public static void setAllFilms(List<Film> allFilms) {
        Bean.allFilms = allFilms;
    }

    public static List<Reference> getAllFormats() {
        return allFormats;
    }

    public static void setAllFormats(List<Reference> allFormats) {
        Bean.allFormats = allFormats;
    }

    public static List<Reference> getAllGenres() {
        return allGenres;
    }

    public static void setAllGenres(List<Reference> allGenres) {
        Bean.allGenres = allGenres;
    }

    public static List<Langue> getAllLangues() {
        return allLangues;
    }

    public static void setAllLangues(List<Langue> allLangues) {
        Bean.allLangues = allLangues;
    }

    public static List<Livre> getAllLivres() {
        return allLivres;
    }

    public static void setAllLivres(List<Livre> allLivres) {
        Bean.allLivres = allLivres;
    }

    public static List<Reference> getAllMediatheques() {
        return allMediatheques;
    }

    public static void setAllMediatheques(List<Reference> allMediatheques) {
        Bean.allMediatheques = allMediatheques;
    }

    public static List<Personne> getAllPersonnes() {
        return allPersonnes;
    }

    public static void setAllPersonnes(List<Personne> allPersonnes) {
        Bean.allPersonnes = allPersonnes;
    }

    public static List<Piste> getAllPistes() {
        return allPistes;
    }

    public static void setAllPistes(List<Piste> allPistes) {
        Bean.allPistes = allPistes;
    }
}
