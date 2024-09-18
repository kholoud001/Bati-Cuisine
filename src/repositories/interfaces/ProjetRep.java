package repositories.interfaces;

import entities.Projet;

import java.sql.SQLException;

public interface ProjetRep {
    void save(Projet projet) throws SQLException;
}
