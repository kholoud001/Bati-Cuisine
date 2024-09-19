package repositories.implementations;

import DAO.implementations.MainOeuvreDAOImp;
import DAO.interfaces.MainOeuvreDAO;
import entities.MainOeuvre;
import repositories.interfaces.MainOeuvreRep;

import java.sql.SQLException;

public class MainOeuvreRepImp implements MainOeuvreRep {

    private MainOeuvreDAO mainOeuvreDAO;

    public MainOeuvreRepImp() throws SQLException {
        this.mainOeuvreDAO=new MainOeuvreDAOImp();
    }

    public void save(MainOeuvre mainOeuvre) throws SQLException {
        mainOeuvreDAO.add(mainOeuvre);
    }
}
