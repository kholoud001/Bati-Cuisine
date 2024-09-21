package services.interfaces;

import entities.Materiel;
import entities.Projet;

import java.sql.SQLException;
import java.util.Collection;

public interface MaterielService {

    void addMateriel(Materiel materiel) throws SQLException;
    Collection<Materiel> getAllMaterielByProjectId(Projet projet) throws SQLException;
}
