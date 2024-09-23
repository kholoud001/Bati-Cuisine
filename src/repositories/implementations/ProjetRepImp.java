package repositories.implementations;

import DAO.implementations.ProjetDAOImp;
import DAO.interfaces.ProjetDAO;
import entities.Projet;
import repositories.interfaces.ProjetRep;

import java.sql.SQLException;

public class ProjetRepImp implements ProjetRep {

    private ProjetDAO projetDAO;

    public ProjetRepImp(ProjetDAO projetDAO) throws SQLException {
        this.projetDAO = projetDAO;
    }

    public Projet save(Projet projet) throws SQLException {
        return  projetDAO.add(projet);
    }

    public void update(Projet projet) throws SQLException {
        projetDAO.updateProject(projet);
    }

    public Projet getProjetById(int id) throws SQLException {
        return projetDAO.getProjetById(id);
    }
}
