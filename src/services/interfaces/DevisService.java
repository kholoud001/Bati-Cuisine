package services.interfaces;

import entities.Devis;

public interface DevisService {

    void saveDevis(Devis devis)  throws  Exception;
    Devis getDevisByProjetId(int id)  throws  Exception;
    void accepterDevis(int devisId)  throws  Exception;
    void refuserDevis(int devisId)  throws  Exception;
}
