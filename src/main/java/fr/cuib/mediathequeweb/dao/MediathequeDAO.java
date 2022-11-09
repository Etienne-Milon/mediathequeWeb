package fr.cuib.mediathequeweb.dao;

import fr.cuib.mediathequeweb.metier.Mediatheque;
import fr.cuib.mediathequeweb.metier.Reference;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MediathequeDAO extends DAO<Reference, Object> {
    protected MediathequeDAO(Connection connexion) {
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
            // Determine the column set column
            String strCmd = "SELECT * from mediatheque order by nom";
            ResultSet rs = stmt.executeQuery(strCmd);

            while (rs.next()) {
                Mediatheque mediatheque = new Mediatheque();
                mediatheque.setId(rs.getInt(1));
                mediatheque.setNom(rs.getString(2));
                liste.add(mediatheque);
            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;

    }

    @Override
    public boolean insert(Reference genre) {
        try (Statement stmt = connexion.createStatement()) {

            String sql = "insert into Mediatheque (nom) values ('" + genre.getNom() + "')";
            stmt.execute(sql);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList getLike(Object objet) {
        return new ArrayList<>();
    }

    @Override
    public boolean update(Reference object) {
        return false;
    }

    @Override
    public boolean delete(Reference object) {
        return false;
    }


}
