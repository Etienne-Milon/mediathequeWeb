package fr.cuib.mediathequeweb.dao;

import fr.cuib.mediathequeweb.metier.Compte;
import fr.cuib.mediathequeweb.metier.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtilisateurDAO extends DAO<Utilisateur, Utilisateur> {

    protected UtilisateurDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Utilisateur getById(long ean13) {
        return null;
    }

    public ArrayList<Utilisateur> getById(Compte compte){
        String query = "SELECT * FROM UTILISATEUR WHERE NUM_ADHERENT = ?";
        ArrayList<Utilisateur> liste = new ArrayList<>();
        ResultSet rs;
        try(PreparedStatement stmt = connexion.prepareStatement(query)){
            stmt.setInt(1, compte.getNumAdherent());
            stmt.execute();
            rs = stmt.getResultSet();
            while(rs.next()){
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(rs.getInt(1));
                utilisateur.setNom(rs.getString(2));
                utilisateur.setIdCompte(rs.getInt(3));
                liste.add(utilisateur);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Utilisateur> getAll() {
        return null;
    }

    @Override
    public ArrayList<Utilisateur> getLike(Utilisateur objet) {
        return null;
    }

    @Override
    public boolean insert(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO UTILISATEUR(NOM_UTILISATEUR, NUM_ADHERENT) VALUES (?,?)";
        try(PreparedStatement stmt = connexion.prepareStatement(query)){
            stmt.setString(1, utilisateur.getNom());
            stmt.setInt(2, utilisateur.getIdCompte());
            stmt.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Utilisateur object) {
        return false;
    }

    @Override
    public boolean delete(Utilisateur object) {
        return false;
    }
}
