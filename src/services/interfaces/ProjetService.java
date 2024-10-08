package services.interfaces;

import entities.Projet;

import java.sql.SQLException;
import java.util.HashMap;

public interface ProjetService {
    Projet createProject(Projet projet) throws SQLException;
    void updateProject(Projet projet) throws SQLException;
    Projet getProjectById(int id) throws SQLException;
    HashMap<Integer, Projet> getAllProjets() throws SQLException;
}
