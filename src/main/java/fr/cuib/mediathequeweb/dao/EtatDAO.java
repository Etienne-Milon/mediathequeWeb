package fr.cuib.mediathequeweb.dao;

import fr.cuib.mediathequeweb.metier.Etat;
import fr.cuib.mediathequeweb.metier.Reference;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EtatDAO extends DAO <Reference,Object>{
    protected EtatDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Reference getById(long ean13) {
        return null;
    }

    @Override
    public ArrayList<Reference> getAll() {
        ArrayList<Reference> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT * FROM ETAT";
            ResultSet rs = stmt.executeQuery(strCmd);
            while (rs.next()) {
                Etat etat = new Etat();
                etat.setId(rs.getInt(1));
                etat.setNom(rs.getString(2));
                liste.add(etat);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList getLike(Object objet) {
        return new ArrayList<>();
    }

    @Override
    public boolean insert(Reference etat) {
        return false;
    }

    @Override
    public boolean update(Reference etat) {
        return false;
    }

    @Override
    public boolean delete(Reference etat) {
        return false;
    }

}
