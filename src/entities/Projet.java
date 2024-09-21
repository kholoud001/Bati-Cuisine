package entities;

import enums.EtatProjet;

public class Projet {
    private int id;
    private String nomProjet;
    private double surface;
    private double tvaProjet;
    private double margeBeneficiaire;
    private double coutTotal;
    private EtatProjet etatProjet;
    private Client client;
    
    public Projet( String nomProjet,double surface, double tvaProjet, double margeBeneficiaire, double coutTotal, EtatProjet etatProjet, Client client) {
        this.nomProjet = nomProjet;
        this.surface = surface;
        this.tvaProjet = tvaProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.client = client;
    }

    public Projet(int id){
        this.id = id;
    }

    public Projet( int id,String nomProjet,double surface, double tvaProjet, double margeBeneficiaire, double coutTotal, EtatProjet etatProjet, Client client) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.surface = surface;
        this.tvaProjet = tvaProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.client = client;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public EtatProjet getEtatProjet() {
        return etatProjet;
    }

    public void setEtatProjet(EtatProjet etatProjet) {
        this.etatProjet = etatProjet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public double getTvaProjet() {
        return tvaProjet;
    }

    public void setTvaProjet(double tvaProjet) {
        this.tvaProjet = tvaProjet;
    }
}
