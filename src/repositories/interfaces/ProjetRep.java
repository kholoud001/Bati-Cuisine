package repositories.interfaces;

import entities.Projet;

import java.sql.SQLException;
import java.util.HashMap;

public interface ProjetRep {
    Projet save(Projet projet) throws SQLException;
    void update(Projet projet) throws SQLException;
    Projet getProjetById(int id) throws SQLException;
    HashMap<Integer, Projet> getProjets() throws SQLException;
}
