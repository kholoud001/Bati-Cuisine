package repositories.implementations;

import DAO.implementations.ProjetDAOImp;
import DAO.interfaces.ProjetDAO;
import entities.Projet;
import repositories.interfaces.ProjetRep;

import java.sql.SQLException;

public class ProjetRepImp implements ProjetRep {

    private ProjetDAO projetDAO;

    public ProjetRepImp() throws SQLException {
        this.projetDAO = new ProjetDAOImp();
    }

    public Projet save(Projet projet) throws SQLException {
        return  projetDAO.add(projet);
    }

    public void update(Projet projet) throws SQLException {
        projetDAO.updateProject(projet);
    }
}
