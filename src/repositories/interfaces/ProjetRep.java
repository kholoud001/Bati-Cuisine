package repositories.interfaces;

import entities.Projet;

import java.sql.SQLException;

public interface ProjetRep {
    Projet save(Projet projet) throws SQLException;
    void update(Projet projet) throws SQLException;
}
