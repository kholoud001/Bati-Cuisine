package repositories.interfaces;

import entities.MainOeuvre;
import entities.Projet;

import java.sql.SQLException;
import java.util.Collection;

public interface MainOeuvreRep {

    void save(MainOeuvre mainOeuvre) throws SQLException;
    Collection<MainOeuvre> getMainByProject(Projet projet)throws SQLException;
}
