package repositories.interfaces;

import entities.MainOeuvre;

import java.sql.SQLException;

public interface MainOeuvreRep {

    void save(MainOeuvre mainOeuvre) throws SQLException;
}
