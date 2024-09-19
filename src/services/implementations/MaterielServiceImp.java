package services.implementations;

import entities.Materiel;
import repositories.implementations.MaterielRepImp;
import repositories.interfaces.MaterielRep;
import services.interfaces.MaterielService;

import java.sql.SQLException;

public class MaterielServiceImp implements MaterielService {

    private MaterielRep materielRep;

    public MaterielServiceImp() throws SQLException {
        this.materielRep= new MaterielRepImp();
    }

    public void addMateriel(Materiel materiel) throws SQLException {
        materielRep.save(materiel);
    }
}
