package repositories.interfaces;

import entities.Materiel;

import java.sql.SQLException;

public interface MaterielRep {
    void save(Materiel materiel) throws SQLException;
}
