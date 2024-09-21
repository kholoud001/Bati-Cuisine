package services.implementations;

import entities.Projet;
import repositories.implementations.ProjetRepImp;
import repositories.interfaces.ProjetRep;
import services.interfaces.ProjetService;

import java.sql.SQLException;

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


}
