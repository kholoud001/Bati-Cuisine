package services.interfaces;

import entities.Projet;

import java.sql.SQLException;

public interface ProjetService {
    void createProject(Projet projet) throws SQLException;
}
