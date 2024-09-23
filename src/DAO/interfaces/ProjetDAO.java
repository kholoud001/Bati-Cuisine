package DAO.interfaces;

import entities.Projet;

import java.sql.SQLException;

public interface ProjetDAO {
    Projet add(Projet projet) throws SQLException;
    void updateProject(Projet projet) throws SQLException;
    Projet getProjetById(int id) throws SQLException;
}
