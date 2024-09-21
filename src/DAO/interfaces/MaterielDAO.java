package DAO.interfaces;

import entities.Materiel;
import entities.Projet;

import java.sql.SQLException;
import java.util.Collection;

public interface MaterielDAO {
    void add(Materiel materiel) throws SQLException;
    Collection<Materiel> getMatrielByProjectId(Projet projet) throws SQLException;
}
