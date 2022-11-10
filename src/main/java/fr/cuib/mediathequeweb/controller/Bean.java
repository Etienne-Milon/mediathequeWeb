package fr.cuib.mediathequeweb.controller;

import fr.cuib.mediathequeweb.dao.DaoFactory;
import fr.cuib.mediathequeweb.metier.*;
import fr.cuib.mediathequeweb.service.ArticleSearch;
import fr.cuib.mediathequeweb.service.ArticleSearch;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.model.menu.MenuModel;

import java.io.Serializable;
import java.util.*;

@Named("bean")
@ViewScoped
public class Bean implements Serializable {

    private Article articleSelected;
    private List<Article> filteredArticles;
    private Editeur editeur;
    private Etat etat;
    private Film film;
    private Format format;
    private Genre genre;
    private Langue langue;
    private Livre livre;
    private Mediatheque mediatheque;
    private Personne personne;
    private Piste piste;
    private Serie serie;

    private List<Article> allArticles;
    private List<Editeur> allEditeurs;
    private List<Reference> allEtats;
    private List<Exemplaire> allExemplaires;
    private List<Film> allFilms;
    private List<Reference> allFormats;
    private List<Reference> allGenres;
    private List<Langue> allLangues;
    private List<Livre> allLivres;
    private List<Reference> allMediatheques;
    private List<Personne> allPersonnes;
    private List<Piste> allPistes;
    private List<Article> recommandedArticles;
    private List<Integer> listFilm = new ArrayList<>(Arrays.asList(1,2,3));
    private List<Integer> listLivre = new ArrayList<>(Arrays.asList(4,5,6));
    private List<Integer> listMusique = new ArrayList<>(Arrays.asList(7,8,9));
    private ArticleSearch articleSearch;

    /* id = EAN13 issu de l'URL*/
    private String id;

    @PostConstruct
    private void init()
    {
        articleSearch = new ArticleSearch();
        articleSearch.setString("");
        articleSearch.setFormat(new Reference());
        articleSearch.setGenre(new Reference());
        articleSearch.setMediatheque(new Reference());
        this.filteredArticles = DaoFactory.getArticleDAO().getLike(articleSearch);

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
        recommandedArticles = randomRecommandedArticles();

        //this.filteredArticles = allArticles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void displayParam() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        id =  params.get("id");
        initArticleSelected(id);
    }

    public void initArticleSelected(String ean13String){
        Long ean13 = Long.parseLong(ean13String);
        articleSelected = DaoFactory.getArticleDAO().getById(ean13);
        articleSelected.setFormat(DaoFactory.getFormatDAO().getById(ean13));
        articleSelected.setEditeur(DaoFactory.getEditeurDAO().getById(ean13));
        articleSelected.setExemplaires(DaoFactory.getExemplaireDAO().getExemplaireById(ean13));
    }

    public List<Article> getFilteredArticles() {
        return filteredArticles;
    }

    public void setFilteredArticles(List<Article> filteredArticles) {
        this.filteredArticles = filteredArticles;
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

    public List<Article> getAllArticles() {
        return allArticles;
    }

    public void setAllArticles(List<Article> allArticles) {
        this.allArticles = allArticles;
    }

    public List<Editeur> getAllEditeurs() {
        return allEditeurs;
    }

    public void setAllEditeurs(List<Editeur> allEditeurs) {
        this.allEditeurs = allEditeurs;
    }

    public List<Reference> getAllEtats() {
        return allEtats;
    }

    public void setAllEtats(List<Reference> allEtats) {
        this.allEtats = allEtats;
    }

    public List<Exemplaire> getAllExemplaires() {
        return allExemplaires;
    }

    public void setAllExemplaires(List<Exemplaire> allExemplaires) {
        this.allExemplaires = allExemplaires;
    }

    public List<Film> getAllFilms() {
        return allFilms;
    }

    public void setAllFilms(List<Film> allFilms) {
        this.allFilms = allFilms;
    }

    public List<Reference> getAllFormats() {
        return allFormats;
    }

    public void setAllFormats(List<Reference> allFormats) {
        this.allFormats = allFormats;
    }

    public List<Reference> getAllGenres() {
        return allGenres;
    }

    public void setAllGenres(List<Reference> allGenres) {
        this.allGenres = allGenres;
    }

    public  List<Langue> getAllLangues() {
        return allLangues;
    }

    public void setAllLangues(List<Langue> allLangues) {
        this.allLangues = allLangues;
    }

    public List<Livre> getAllLivres() {
        return allLivres;
    }

    public void setAllLivres(List<Livre> allLivres) {
        this.allLivres = allLivres;
    }

    public List<Reference> getAllMediatheques() {
        return allMediatheques;
    }

    public  void setAllMediatheques(List<Reference> allMediatheques) {
        this.allMediatheques = allMediatheques;
    }

    public List<Personne> getAllPersonnes() {
        return allPersonnes;
    }

    public void setAllPersonnes(List<Personne> allPersonnes) {
        this.allPersonnes = allPersonnes;
    }

    public List<Piste> getAllPistes() {
        return allPistes;
    }

    public void setAllPistes(List<Piste> allPistes) {
        this.allPistes = allPistes;
    }

    public List<Article> getRecommandedArticles() {
        return recommandedArticles;
    }

    public void setRecommandedArticles(List<Article> recommandedArticles) {
        this.recommandedArticles = recommandedArticles;
    }

    public List<Integer> getListFilm() {
        return listFilm;
    }

    public void setListFilm(List<Integer> listFilm) {
        this.listFilm = listFilm;
    }

    public List<Integer> getListLivre() {
        return listLivre;
    }

    public void setListLivre(List<Integer> listLivre) {
        this.listLivre = listLivre;
    }

    public List<Integer> getListMusique() {
        return listMusique;
    }

    public void setListMusique(List<Integer> listMusique) {
        this.listMusique = listMusique;
    }

    private List<Article> randomRecommandedArticles(){
        List<Article> list = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < 8; i++){
            Article articleRecommanded = allArticles.get(random.nextInt(allArticles.size()));
            list.add(articleRecommanded);
        }
        return list;
    }
}
