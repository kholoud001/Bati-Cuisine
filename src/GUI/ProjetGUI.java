package GUI;

import entities.Client;
import entities.MainOeuvre;
import entities.Materiel;
import entities.Projet;
import enums.EtatProjet;

import services.implementations.CoutServiceImp;
import services.interfaces.CoutService;
import services.interfaces.MainOeuvreService;
import services.interfaces.MaterielService;
import services.interfaces.ProjetService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

public class ProjetGUI {

    private final ProjetService projetService;
    private MaterielService materielService;
    private MainOeuvreService mainOeuvreService;
    private ComposantGUI composantGUI ;
    private  CoutService coutService;
    private DevisGUI devisGUI;
    private Scanner scanner;


    public ProjetGUI(Scanner scanner, ProjetService projetService,ComposantGUI composantGUI, DevisGUI devisGUI,
                     MaterielService materielService, MainOeuvreService mainOeuvreService,CoutService coutService) throws SQLException {
        this.projetService=projetService;
        this.composantGUI= composantGUI;
        this.devisGUI=devisGUI;
        this.materielService=materielService;
        this.mainOeuvreService=mainOeuvreService;
        this.coutService=coutService;
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

        composantGUI.addMaterialsLoop(projet);
        //ajout main oeuvre
        composantGUI.addLaborLoop(projet);

        System.out.println("Calcul du coût en cours...");

        //tva
        System.out.println("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        String response = scanner.nextLine().toLowerCase();
        double tvaProjet = 0;
        if (response.equals("y")) {
            System.out.print("Entrez le pourcentage de TVA (%) :  ");

            // Read the TVA percentage
            while (true) {
                if (scanner.hasNextDouble()) {
                    tvaProjet = scanner.nextDouble();
                    scanner.nextLine(); // Clear the newline
                    break; // Exit the loop when valid input is received
                } else {
                    System.out.println("Veuillez entrer un pourcentage valide.");
                    scanner.nextLine(); // Clear invalid input
                }
            }
        }
        System.out.printf("tva test ==========> %.2f\n", tvaProjet);
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
        calculCout(projet, tvaProjet, margeBeneficiaire);

        projetService.updateProject(projet);
        devisGUI.createDevis(projet);
        //System.out.println("Project added successfully.");
    }


    public void calculCout(Projet projet, double tvaProjet, double margeBeneficiaire) throws SQLException {
        System.out.println("\n--- Résultat du Calcul ---\n");
        System.out.printf("Nom du projet : %s \n", projet.getNomProjet());
        System.out.printf("Client : %s \n", projet.getClient().getName());
        System.out.printf("Adresse du chantier : %s \n", projet.getClient().getAddress());
        System.out.printf("Surface : %.2f m² \n", projet.getSurface());

        // Calcul des coûts des matériaux
        System.out.println("\n--- Détail des Coûts ---\n1. Matériaux :");
        Collection<Materiel> materiaux = materielService.getAllMaterielByProjectId(projet);
        double coutMateriaux = coutService.totalCostMateriel(materiaux);
        for (Materiel materiel : materiaux) {
            double cout = coutService.coutMateriel(materiel);
            System.out.printf("- %s : %.2f € (quantité : %.2f, coût unitaire : %.2f €/unité, qualité : %.2f, transport : %.2f €)\n",
                    materiel.getNom(), cout, materiel.getQuantite(), materiel.getCoutUnitaire(),
                    materiel.getCoefficientQualite(), materiel.getCoutTransport());
        }
        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**\n", coutMateriaux);

        // Application de la TVA aux matériaux
        if (tvaProjet > 0) {
            double coutMateriauxTVA = coutMateriaux * (1 + tvaProjet / 100);
            System.out.printf("**Coût total des matériaux avec TVA (%.2f%%) : %.2f €**\n", tvaProjet, coutMateriauxTVA);
        } else {
            System.out.printf("**Coût total des matériaux avec TVA (0.00%%) : %.2f €**\n", coutMateriaux);
        }

        // Calcul des coûts de la main-d'œuvre
        System.out.println("\n2. Main-d'œuvre :");
        Collection<MainOeuvre> mainOeuvres = mainOeuvreService.getAllMainOeuvre(projet);
        double coutMainOeuvre = coutService.totalCostMainOeuvre(mainOeuvres);
        for (MainOeuvre mo : mainOeuvres) {
            double cout = coutService.coutMainOeuvre(mo);
            System.out.printf("- %s : %.2f € (taux horaire : %.2f €/h, heures travaillées : %.2f h, productivité : %.2f)\n",
                    mo.getNom(), cout, mo.getTauxHoraire(), mo.getHeuresTravail(), mo.getProductiviteOuvrier());
        }
        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**\n", coutMainOeuvre);

        // Application de la TVA à la main-d'œuvre
        if (tvaProjet > 0) {
            double coutMainOeuvreTVA = coutMainOeuvre * (1 + tvaProjet / 100);
            System.out.printf("**Coût total de la main-d'œuvre avec TVA (%.2f%%) : %.2f €**\n", tvaProjet, coutMainOeuvreTVA);
        } else {
            System.out.printf("**Coût total de la main-d'œuvre avec TVA (0.00%%) : %.2f €**\n\n", coutMainOeuvre);
        }

        // Calcul final avec marge bénéficiaire
        double coutTotal = coutMateriaux + coutMainOeuvre; // Total avant TVA
        if (tvaProjet > 0) {
            coutTotal += (coutTotal * tvaProjet / 100); // Ajout de la TVA
        }

        System.out.printf("3. Coût total avant marge : %.2f €\n", coutTotal);
        double coutMarge =  coutTotal *(margeBeneficiaire/100);
        System.out.printf("4. Marge bénéficiaire (%.2f): %.2f €\n", margeBeneficiaire,coutMarge);


        if(margeBeneficiaire>0) {
            coutTotal *= (1 + margeBeneficiaire / 100);
            System.out.printf("**Coût total final du projet: %.2f €**\n", coutTotal);
        }else{
            System.out.printf("**Coût total final du projet: %.2f €**\n", coutTotal);
        }

        projet.setCoutTotal(coutTotal);
    }






}
