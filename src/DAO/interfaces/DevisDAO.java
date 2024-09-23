package DAO.interfaces;

import entities.Devis;

import java.sql.SQLException;

public interface DevisDAO {
    void add(Devis devis) throws SQLException;
    Devis getDevisByProjetId(int projetId) throws SQLException;
    void accepterDevis(int devisId) throws SQLException;
    void refuserDevis(int devisId) throws SQLException;
}
