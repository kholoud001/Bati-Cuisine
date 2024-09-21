package services.interfaces;

import entities.MainOeuvre;
import entities.Projet;

import java.sql.SQLException;
import java.util.Collection;

public interface MainOeuvreService {
    void createMainOeuvre(MainOeuvre mainOeuvre) throws SQLException;
    Collection<MainOeuvre> getAllMainOeuvre(Projet projet) throws SQLException;
}
