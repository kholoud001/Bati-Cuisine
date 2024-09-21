package services.interfaces;

import entities.MainOeuvre;
import entities.Materiel;

import java.util.Collection;

public interface CoutService {
    double coutMateriel(Materiel materiel);
    double totalCostMateriel(Collection<Materiel> materiels);
    double costMateriauTva(double totalMateriau, double tva);
    double coutMainOeuvre(MainOeuvre mainOeuvre);
    double totalCostMainOeuvre(Collection<MainOeuvre> mainOeuvres);
}
