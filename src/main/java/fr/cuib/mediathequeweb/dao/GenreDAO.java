package fr.cuib.mediathequeweb.dao;

import fr.cuib.mediathequeweb.metier.*;

import java.sql.*;
import java.util.ArrayList;

public class GenreDAO extends DAO<Reference, Object> {
    protected GenreDAO(Connection connexion) {
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
            String strCmd = "SELECT * FROM GENRE order by nom_genre";
            ResultSet rs = stmt.executeQuery(strCmd);
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getInt(1));
                genre.setNom(rs.getString(2));

                liste.add(genre);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public ArrayList<Reference> getAllRef(Article article) {
        ArrayList<Reference> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT * FROM GENRE EXCEPT SELECT GENRE.ID_GENRE, GENRE.NOM_GENRE FROM GENRE JOIN AVOIR_GENRE ON GENRE.ID_GENRE = AVOIR_GENRE.ID_GENRE " +
                    "JOIN ARTICLE ON AVOIR_GENRE.EAN13 = ARTICLE.EAN13 WHERE ARTICLE.EAN13 = " + article.getEAN13();
            ResultSet rs = stmt.executeQuery(strCmd);
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getInt(1));
                genre.setNom(rs.getString(2));

                liste.add(genre);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Reference> getLike(Object objet) {
        return new ArrayList<>();
    }


    @Override
    public boolean insert(Reference genre) {
        String sql = "insert into Genre (nom_genre) values (?)";
        try (PreparedStatement stmt = connexion.prepareStatement(sql)) {
            stmt.setString(1, genre.getNom());
            stmt.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reference reference) {
        return false;
    }

    @Override
    public boolean delete(Reference genre) {
        String sql = "delete from GENRE WHERE id_genre = ?";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, genre.getId());
            pstmt.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Reference> getGenre(Article article) {
        ResultSet rs;
        ArrayList<Reference> liste = new ArrayList<>();
        String Statement = "SELECT avoir_genre.id_genre,nom_genre from ARTICLE\n" +
                "join avoir_genre on ARTICLE.EAN13 = avoir_genre.EAN13\n" +
                "join genre on avoir_genre.id_genre = genre.id_genre\n" +
                "where article.EAN13 = ?";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(Statement)) {
            pStmt.setLong(1, article.getEAN13());
            pStmt.execute();
            rs = pStmt.getResultSet();
            while (rs.next()) {
                Genre newGenre = new Genre();
                newGenre.setId(rs.getInt(1));
                newGenre.setNom(rs.getString(2));
                liste.add(newGenre);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public boolean insertAvoirGenre(Article article) throws SQLException {
        String query = "INSERT INTO AVOIR_GENRE (EAN13,id_genre) VALUES (?,?)";
        try (PreparedStatement stmt = connexion.prepareStatement(query)) {
            connexion.setAutoCommit(false);
            for (Reference reference : article.getGenres()) {
                stmt.setLong(1, article.getEAN13());
                stmt.setInt(2, reference.getId());
                stmt.executeUpdate();
            }
            connexion.commit();
            return true;
        } catch (SQLException ex) {
            connexion.rollback();
            return false;
        }
    }
}
