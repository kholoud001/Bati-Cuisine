package entities;

public class MainOeuvre extends Composant{
    private double tauxHoraire;
    private double heuresTravail;
    private double productiviteOuvrier;

//    public MainOeuvre(int id, String nom, double tauxTVA, String typeComposant, Projet projet,double tauxHoraire, double heuresTravail, double productiviteOuvrier) {
//
//        super(id, nom, tauxTVA, typeComposant,projet);
//        this.tauxHoraire = tauxHoraire;
//        this.heuresTravail = heuresTravail;
//        this.productiviteOuvrier = productiviteOuvrier;
//    }

    public MainOeuvre( String nom, double tauxTVA, String typeComposant, Projet projet,double tauxHoraire, double heuresTravail, double productiviteOuvrier) {
        super( nom, tauxTVA, typeComposant,projet);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(double heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }
}
