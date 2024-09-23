package repositories.implementations;

import DAO.interfaces.DevisDAO;
import entities.Devis;
import repositories.interfaces.DevisRep;

import java.sql.SQLException;

public class DevisRepImp implements DevisRep {

    private DevisDAO devisDAO;

    public DevisRepImp(DevisDAO devisDAO) {
        this.devisDAO = devisDAO;
    }

    public void save(Devis devis) throws Exception {
        devisDAO.add(devis);
    }

    public Devis getDevisByProjetId(int id) throws Exception {
        return devisDAO.getDevisByProjetId(id);
    }

    public void accepterDevis(int devisId) throws SQLException{
        devisDAO.accepterDevis(devisId);
    }

    public void refuserDevis(int devisId) throws SQLException{
        devisDAO.refuserDevis(devisId);
    }
}
