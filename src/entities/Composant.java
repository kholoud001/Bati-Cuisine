package entities;

public abstract class Composant {
    private int id;
    private String nom;
    private double tauxTVA;
    private String typeComposant;


    public static final String Type_Materiel="Materiel";
    public static final String Type_Main="Main-d'oeuvre";

    public Composant(int id, String nom, double tauxTVA, String typeComposant) {
        this.id = id;
        this.nom = nom;
        this.tauxTVA = tauxTVA;
        setTypeComposant(typeComposant);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(double tauxTVA) {
        this.tauxTVA = tauxTVA;
    }

    public String getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(String typeComposant) {
        if(Type_Materiel.equals(typeComposant) || Type_Main.equals(typeComposant)){
            this.typeComposant = typeComposant;
        }else {
            System.out.println("TypeComposant Incorrecte.il faut être  'Matériel' ou 'Main-d'œuvre'");
        }
    }
}
