package DAO.interfaces;

import entities.Devis;

import java.sql.SQLException;

public interface DevisDAO {
    void add(Devis devis) throws SQLException;
}
