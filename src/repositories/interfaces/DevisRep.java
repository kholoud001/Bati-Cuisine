package repositories.interfaces;

import entities.Devis;

import java.sql.SQLException;

public interface DevisRep {
    void save(Devis devis) throws Exception;
    Devis getDevisByProjetId(int id) throws Exception;
    void accepterDevis(int devisId) throws SQLException;
    void refuserDevis(int devisId) throws SQLException;
}
