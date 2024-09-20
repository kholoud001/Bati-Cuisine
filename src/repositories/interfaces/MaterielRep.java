package repositories.interfaces;

import entities.Materiel;
import entities.Projet;

import java.sql.SQLException;
import java.util.Collection;

public interface MaterielRep {
    void save(Materiel materiel) throws SQLException;
    Collection<Materiel> getAllbyProductId(Projet projet) throws SQLException;
}
