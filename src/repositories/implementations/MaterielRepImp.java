package repositories.implementations;

import DAO.implementations.MaterielDAOImp;
import DAO.interfaces.MaterielDAO;
import entities.Materiel;
import repositories.interfaces.MaterielRep;

import java.sql.SQLException;

public class MaterielRepImp implements MaterielRep {

    private MaterielDAO materielDAO;

    public MaterielRepImp() throws SQLException {
        this.materielDAO = new MaterielDAOImp();
    }

    public void save(Materiel materiel) throws SQLException {
        materielDAO.add(materiel);
    }
}
