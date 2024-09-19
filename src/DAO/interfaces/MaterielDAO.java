package DAO.interfaces;

import entities.Materiel;

import java.sql.SQLException;

public interface MaterielDAO {
    void add(Materiel materiel) throws SQLException;
}
