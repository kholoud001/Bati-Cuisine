package services.interfaces;

import entities.MainOeuvre;

import java.sql.SQLException;

public interface MainOeuvreService {
    void createMainOeuvre(MainOeuvre mainOeuvre) throws SQLException;
}
