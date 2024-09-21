package services.implementations;

import entities.Materiel;
import entities.Projet;
import repositories.implementations.MaterielRepImp;
import repositories.interfaces.MaterielRep;
import services.interfaces.MaterielService;

import java.sql.SQLException;
import java.util.Collection;

public class MaterielServiceImp implements MaterielService {

    private MaterielRep materielRep;

    public MaterielServiceImp( MaterielRep materielRep) throws SQLException {
        this.materielRep= materielRep;
    }

    public void addMateriel(Materiel materiel) throws SQLException {
        materielRep.save(materiel);
    }

    public Collection<Materiel> getAllMaterielByProjectId(Projet projet) throws SQLException {
        return materielRep.getAllbyProductId(projet);
    }
}
