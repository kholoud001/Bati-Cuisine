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
}
