package services.implementations;

import entities.Projet;
import repositories.implementations.ProjetRepImp;
import repositories.interfaces.ProjetRep;
import services.interfaces.ProjetService;

import java.sql.SQLException;

public class ProjetServiceImp implements ProjetService {

    private ProjetRep projetRep;

    public ProjetServiceImp() throws SQLException {
        this.projetRep=new ProjetRepImp();
    }

    public void createProject(Projet projet) throws SQLException {
        projetRep.save(projet);
    }
}
