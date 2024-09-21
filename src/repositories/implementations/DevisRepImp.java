package repositories.implementations;

import DAO.interfaces.DevisDAO;
import entities.Devis;
import repositories.interfaces.DevisRep;

public class DevisRepImp implements DevisRep {

    private DevisDAO devisDAO;

    public DevisRepImp(DevisDAO devisDAO) {
        this.devisDAO = devisDAO;
    }

    public void save(Devis devis) throws Exception {
        devisDAO.add(devis);
    }
}
