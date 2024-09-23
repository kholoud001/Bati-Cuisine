package services.implementations;

import entities.Projet;
import repositories.implementations.ProjetRepImp;
import repositories.interfaces.ProjetRep;
import services.interfaces.ProjetService;

import java.sql.SQLException;
import java.util.HashMap;

public class ProjetServiceImp implements ProjetService {

    private ProjetRep projetRep;

    public ProjetServiceImp(ProjetRep projetRep) throws SQLException {
        this.projetRep=projetRep;
    }

    public Projet createProject(Projet projet) throws SQLException {
        return projetRep.save(projet);
    }

    public void  updateProject(Projet projet) throws SQLException{
        projetRep.update(projet);
    }

    public Projet getProjectById(int id) throws SQLException {
        return projetRep.getProjetById(id);
    }

    public HashMap<Integer, Projet> getAllProjets() throws SQLException{
        return projetRep.getProjets();
    }

}
