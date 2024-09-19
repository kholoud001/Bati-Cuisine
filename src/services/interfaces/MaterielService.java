package services.interfaces;

import entities.Materiel;

import java.sql.SQLException;

public interface MaterielService {

    void addMateriel(Materiel materiel) throws SQLException;
}
