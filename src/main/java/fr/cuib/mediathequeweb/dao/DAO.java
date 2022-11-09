package fr.cuib.mediathequeweb.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO <T,S>{

    protected Connection connexion;

    protected DAO(Connection connexion) {
        this.connexion = connexion;
    }

    public abstract T getById(long ean13);

    public abstract ArrayList<T> getAll();

    public abstract ArrayList<T> getLike(S objet);

    public abstract boolean insert(T objet) throws SQLException;

    public abstract boolean update(T object);

    public abstract boolean delete(T object);
}
