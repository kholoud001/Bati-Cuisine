package entities;

public class Materiel extends Composant{

    private double coutUnitaire;
    private double coutTransport;
    private double coefficientQualite;
    private double quantite;


    public Materiel( String nom, double tauxTVA, String typeComposant, Projet projet,double coutUnitaire, double coutTransport, double coefficientQualite, double quantite) {
        super(nom, tauxTVA, typeComposant,projet);
        this.coutUnitaire = coutUnitaire;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
        this.quantite = quantite;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
}
