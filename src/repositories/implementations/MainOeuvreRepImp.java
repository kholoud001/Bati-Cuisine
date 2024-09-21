package repositories.implementations;

import DAO.implementations.MainOeuvreDAOImp;
import DAO.interfaces.MainOeuvreDAO;
import entities.MainOeuvre;
import entities.Projet;
import repositories.interfaces.MainOeuvreRep;

import java.sql.SQLException;
import java.util.Collection;

public class MainOeuvreRepImp implements MainOeuvreRep {

    private MainOeuvreDAO mainOeuvreDAO;

    public MainOeuvreRepImp( MainOeuvreDAO mainOeuvreDAO) throws SQLException {
        this.mainOeuvreDAO=mainOeuvreDAO;
    }

    public void save(MainOeuvre mainOeuvre) throws SQLException {
        mainOeuvreDAO.add(mainOeuvre);
    }
    public Collection<MainOeuvre> getMainByProject(Projet projet)throws SQLException {
        return mainOeuvreDAO.getMainOeuvreByProjectId(projet);
    }
}
