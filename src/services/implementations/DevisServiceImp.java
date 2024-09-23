package services.implementations;

import entities.Devis;
import repositories.interfaces.DevisRep;
import services.interfaces.DevisService;

public class DevisServiceImp implements DevisService {

    private DevisRep devisRep;

    public DevisServiceImp(DevisRep devisRep) {
        this.devisRep = devisRep;
    }

    public void saveDevis(Devis devis)  throws  Exception {
        devisRep.save(devis);
    }

    public Devis getDevisByProjetId(int id)  throws  Exception {
        return devisRep.getDevisByProjetId(id);
    }

    public void accepterDevis(int devisId)  throws  Exception {
        devisRep.accepterDevis(devisId);
    }

    public void refuserDevis(int devisId)  throws  Exception {
        devisRep.refuserDevis(devisId);
    }
}
