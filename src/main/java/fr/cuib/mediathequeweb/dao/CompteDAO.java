package fr.cuib.mediathequeweb.dao;

import fr.cuib.mediathequeweb.metier.Compte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompteDAO extends DAO<Compte, Compte>{

    protected CompteDAO(Connection connexion) {
        super(connexion);
    }

    public Compte getById(int id) {
        String query = "SELECT * FROM COMPTE WHERE NUM_ADHERENT = ?";
        Compte compte = new Compte();
        ResultSet rs;
        try(PreparedStatement stmt = connexion.prepareStatement(query)){
            stmt.setLong(1, id);
            stmt.execute();
            rs = stmt.getResultSet();
            while (rs.next()){
                compte.setNumAdherent(rs.getInt(1));
                compte.setNom(rs.getString(2));
                compte.setPrenom(rs.getString(3));
                compte.setAdresse(rs.getString(4));
                compte.setEmail(rs.getString(5));
                compte.setPassword(rs.getString(6));
            }
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return compte;
    }

    public static boolean validatePwd(String compte, long hash){
        return false;
    }


    @Override
    public Compte getById(long ean13) {
        return null;
    }
    @Override
    public ArrayList<Compte> getAll() {
        return null;
    }

    @Override
    public ArrayList<Compte> getLike(Compte objet) {
        return null;
    }

    @Override
    public boolean delete(Compte object) {
        return false;
    }

    @Override
    public boolean update(Compte object) {
        return false;
    }

    @Override
    public boolean insert(Compte objet) throws SQLException {
        return false;
    }
}
