package services.implementations;

import entities.MainOeuvre;
import entities.Materiel;
import services.interfaces.CoutService;

import java.util.Collection;

public class CoutServiceImp implements CoutService {

    public double coutMateriel(Materiel materiel){

        double coutMateriel=(materiel.getQuantite()*materiel.getCoutUnitaire()*materiel.getCoefficientQualite())
                + materiel.getCoutTransport();

        return coutMateriel;
    }

    public double totalCostMateriel(Collection<Materiel> materiels){
        double coutMateriel=0;
        for(Materiel materiel : materiels){
            coutMateriel+=coutMateriel(materiel);
        }
        return coutMateriel;
    }

    public double costMateriauTva(double totalMateriau, double tva){
        double tvaDecimal = tva / 100.0;
        return totalMateriau * (1+tvaDecimal);
    }

    public double coutMainOeuvre(MainOeuvre mainOeuvre) {
        double cout = mainOeuvre.getTauxHoraire() * mainOeuvre.getHeuresTravail() * mainOeuvre.getProductiviteOuvrier();
        return cout;
    }
    public double totalCostMainOeuvre(Collection<MainOeuvre> mainOeuvres) {
        double coutTotal = 0.0;
        for (MainOeuvre mainOeuvre : mainOeuvres) {
            coutTotal += coutMainOeuvre(mainOeuvre);
        }
        return coutTotal;
    }
}
