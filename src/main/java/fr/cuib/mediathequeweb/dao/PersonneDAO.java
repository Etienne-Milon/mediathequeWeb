package fr.cuib.mediathequeweb.dao;

import fr.cuib.mediathequeweb.metier.Personne;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonneDAO extends DAO<Personne, Object> {
    protected PersonneDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Personne getById(long ean13) {
        return null;
    }

    @Override
    public ArrayList<Personne> getAll() {
        ArrayList<Personne> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()) {
            String query = "SELECT DISTINCT Personne.id_personne, Personne.nom FROM Personne ORDER BY nom";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Personne personne = new Personne();
                personne.setId(rs.getInt(1));
                personne.setNom(rs.getString(2));
                liste.add(personne);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Personne> getLike(Object objet) {
        return new ArrayList<>();
    }

    @Override
    public boolean insert(Personne objet) {
        return false;
    }

    @Override
    public boolean update(Personne object) {
        return false;
    }

    @Override
    public boolean delete(Personne object) {
        return false;
    }


}
