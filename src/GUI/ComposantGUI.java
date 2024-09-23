package GUI;

import entities.MainOeuvre;
import entities.Materiel;
import entities.Projet;
import services.interfaces.MainOeuvreService;
import services.interfaces.MaterielService;

import java.sql.SQLException;
import java.util.Scanner;

public class ComposantGUI {

    private final MaterielService materielService;
    private final MainOeuvreService mainOeuvreService;
    private Scanner scanner;

    public ComposantGUI(Scanner scanner, MainOeuvreService mainOeuvreService, MaterielService materielService) {
        this.materielService = materielService;
        this.mainOeuvreService = mainOeuvreService;
        this.scanner = scanner;
    }

    //loops
    void addMaterialsLoop(Projet projet) throws SQLException {
        String choixMat;
        do {
            addMaterial(projet);
            System.out.println("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            choixMat = scanner.nextLine().toLowerCase();
        } while (choixMat.equals("y"));
    }

    void addLaborLoop(Projet projet) throws SQLException {
        String choixMainOeuvre;
        do {
            addMainOeuvre(projet);
            System.out.println("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            choixMainOeuvre = scanner.nextLine().toLowerCase();
        } while (choixMainOeuvre.equals("y"));
    }

    //add material
    private void addMaterial(Projet projet) throws SQLException {
        System.out.println("\n--- Ajout des matériaux ---\n");
        System.out.print("Entrez le nom du matériau :");
        String nomMat = scanner.nextLine();

        System.out.print("Entrez la quantité de ce matériau (en m²) : ");
        double quantite = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Entrez le coût unitaire de ce matériau (€/m²) :");
        double cout_unitaire = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Entrez le coût de transport de ce matériau (€) :");
        double cout_tranport = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
        double coef=scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Entrez le taux TVA du matériel: ");
        double tva=scanner.nextDouble();
        scanner.nextLine();

        Materiel materiel= new Materiel(nomMat,tva,"Materiel",projet,cout_unitaire,cout_tranport,coef,quantite);
        materielService.addMateriel(materiel);
        System.out.println("\nMatériau ajouté avec succès !\n");
    }


    // add main oeuvre
    private void addMainOeuvre(Projet projet) throws SQLException {
        System.out.println("\n--- Ajout de la main-d'œuvre ---\n");
        System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
        String type = scanner.nextLine();

        System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
        double taux = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Entrez le nombre d'heures travaillées : ");
        double heure = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
        double facteur = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Entrez le taux TVA du main d'oeuvre: ");
        double tva=scanner.nextDouble();
        scanner.nextLine();

        MainOeuvre mainOeuvre = new MainOeuvre(type,tva,"Main-d'oeuvre",projet,taux,heure,facteur);
        mainOeuvreService.createMainOeuvre(mainOeuvre);
        System.out.println("\nMain-d'œuvre ajoutée avec succès !\n");

    }

}
