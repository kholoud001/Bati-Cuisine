package repositories.implementations;

import DAO.implementations.MaterielDAOImp;
import DAO.interfaces.MaterielDAO;
import entities.Materiel;
import entities.Projet;
import repositories.interfaces.MaterielRep;

import java.sql.SQLException;
import java.util.Collection;

public class MaterielRepImp implements MaterielRep {

    private MaterielDAO materielDAO;

    public MaterielRepImp(MaterielDAO materielDAO) throws SQLException {
        this.materielDAO = materielDAO;
    }

    public void save(Materiel materiel) throws SQLException {
        materielDAO.add(materiel);
    }

    public Collection<Materiel> getAllbyProductId(Projet projet) throws SQLException {
        return materielDAO.getMatrielByProjectId(projet);
    }
}
