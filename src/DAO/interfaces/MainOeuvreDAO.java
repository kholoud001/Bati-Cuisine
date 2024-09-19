package DAO.interfaces;

import entities.MainOeuvre;

import java.sql.SQLException;

public interface MainOeuvreDAO {

    void add(MainOeuvre mainOeuvre) throws SQLException;
}
