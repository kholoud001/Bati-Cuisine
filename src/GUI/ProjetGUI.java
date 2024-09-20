package GUI;

import entities.Client;
import entities.MainOeuvre;
import entities.Materiel;
import entities.Projet;
import enums.EtatProjet;
import services.implementations.MainOeuvreServiceImp;
import services.implementations.MaterielServiceImp;
import services.implementations.ProjetServiceImp;
import services.interfaces.MainOeuvreService;
import services.interfaces.MaterielService;
import services.interfaces.ProjetService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class ProjetGUI {

    private ProjetService projetService;
    private MaterielService materielService;
    private MainOeuvreService mainOeuvreService;
    private Scanner scanner;

    public ProjetGUI(Scanner scanner, ProjetService projetService,MaterielService materielService, MainOeuvreService mainOeuvreService) throws SQLException {
        this.projetService=projetService;
        this.materielService=materielService;
        this.mainOeuvreService=mainOeuvreService;
        this.scanner=scanner;
    }

    public void addProjet(Client client) throws SQLException {
        System.out.println("--- Création d'un Nouveau Projet ---");
        System.out.print("Entrez le nom du projet: ");
        String nomProjet = scanner.nextLine();

        System.out.print("Entrez la surface de la cuisine (en m²) : ");
        double surface = scanner.nextDouble();
        scanner.nextLine();

        Projet projet = new Projet(nomProjet, surface, 0, 0, 0, EtatProjet.EN_COURS, client);
        projet = projetService.createProject(projet);
        System.out.println("projet => " + projet);


        //partie ajout material
        addMaterialsLoop(projet);
        //ajout main oeuvre
        addLaborLoop(projet);

        System.out.println("Calcul du coût en cours...");



        // Project TVA
        System.out.println("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        String response = scanner.nextLine().toLowerCase();
        double tvaProjet = 0;

        if (response.equals("y")) {
            System.out.println("Entrez le pourcentage de TVA (%) :  ");
            tvaProjet = scanner.nextDouble();
            scanner.nextLine();
        }else{
            tvaProjet = 0;
        }

        // Marge
        System.out.println("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
        String answer= scanner.nextLine().toLowerCase();
        double margeBeneficiaire=0;
        if(answer.equals("y")) {
            System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
            margeBeneficiaire = scanner.nextDouble();
            scanner.nextLine();
        }

        System.out.println("Calcul du coût en cours...");

        //cout totale
        double coutTotal=0;
        scanner.nextLine();

//        System.out.println("Enter project state (EN_COURS, TERMINE, ANNULE): ");
//        String etat = scanner.nextLine();
//        EtatProjet etatProjet = EtatProjet.valueOf(etat.toUpperCase());

        projetService.updateProject(projet);
        System.out.println("Project added successfully.");
    }


    //loops
    private void addMaterialsLoop(Projet projet) throws SQLException {
        String choixMat;
        do {
            addMaterial(projet);
            System.out.println("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            choixMat = scanner.nextLine().toLowerCase();
        } while (choixMat.equals("y"));
    }

    private void addLaborLoop(Projet projet) throws SQLException {
        String choixMainOeuvre;
        do {
            addMainOeuvre(projet);
            System.out.println("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            choixMainOeuvre = scanner.nextLine().toLowerCase();
        } while (choixMainOeuvre.equals("y"));
    }

    //add material
    public void addMaterial(Projet projet) throws SQLException {
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
    public void addMainOeuvre(Projet projet) throws SQLException {
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
