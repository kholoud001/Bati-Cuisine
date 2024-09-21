package DAO.interfaces;

import entities.MainOeuvre;
import entities.Projet;

import java.sql.SQLException;
import java.util.Collection;

public interface MainOeuvreDAO {

    void add(MainOeuvre mainOeuvre) throws SQLException;
    Collection<MainOeuvre> getMainOeuvreByProjectId(Projet projet) throws SQLException;
}
