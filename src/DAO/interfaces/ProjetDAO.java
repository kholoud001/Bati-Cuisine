package DAO.interfaces;

import entities.Projet;

import java.sql.SQLException;

public interface ProjetDAO {
    void add(Projet projet) throws SQLException;
}
