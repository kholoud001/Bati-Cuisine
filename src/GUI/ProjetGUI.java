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
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Veuillez entrer un pourcentage valide.");
                    scanner.nextLine();
                }
            }
        }
        System.out.printf("tva test ==========> %.2f\n", tvaProjet);
        // Marge
        System.out.println("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
        String answer= scanner.nextLine().toLowerCase();
        double margeBeneficiaire=0;
        if (answer.equals("y")) {
            System.out.print("Entrez le pourcentage de la marge bénéficaire (%) :  ");
            while (true) {
                if (scanner.hasNextDouble()) {
                    margeBeneficiaire = scanner.nextDouble();
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Veuillez entrer un pourcentage valide.");
                    scanner.nextLine();
                }
            }
        }
        System.out.printf("margeBeneficiaire test ==========> %.2f\n", margeBeneficiaire);
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
        double coutMateriaux = 0;
        double cout=0;
        for (Materiel materiel : materiaux) {
            cout = coutService.coutMateriel(materiel);  // Calcul du coût sans TVA
            double coutAvecTVA = cout * (1 + materiel.getTauxTVA() / 100);  // Application de la TVA spécifique
            coutMateriaux += coutAvecTVA;  // Ajout au coût total
            System.out.printf("- %s : %.2f € (quantité : %.2f, coût unitaire : %.2f €/unité, qualité : %.2f , transport:  %.2f € )\n",
                    materiel.getNom(), cout, materiel.getQuantite(), materiel.getCoutUnitaire(), materiel.getCoefficientQualite(), materiel.getCoutTransport());
        }
        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**\n", cout);
        System.out.printf("**Coût total des matériaux avec TVA : %.2f €**\n", coutMateriaux);

        // Calcul des coûts de la main-d'œuvre
        System.out.println("\n2. Main-d'œuvre :");
        Collection<MainOeuvre> mainOeuvres = mainOeuvreService.getAllMainOeuvre(projet);
        double coutMainOeuvre = 0;

        for (MainOeuvre mo : mainOeuvres) {
            cout = coutService.coutMainOeuvre(mo);  // Calcul du coût sans TVA
            double coutAvecTVA = cout * (1 + mo.getTauxTVA() / 100);  // Application de la TVA spécifique
            coutMainOeuvre += coutAvecTVA;  // Ajout au coût total
            System.out.printf("- %s : %.2f € (taux horaire : %.2f €/h, heures travaillées : %.2f h,  productivité : %.2f )\n",
                    mo.getNom(), cout, mo.getTauxHoraire(), mo.getHeuresTravail(), mo.getProductiviteOuvrier());
        }
        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**\n", cout);
        System.out.printf("**Coût total de la main-d'œuvre avec TVA : %.2f €**\n", coutMainOeuvre);

        // Calcul final avant application de la TVA du projet
        double coutTotalSansTVAProjet = coutMateriaux + coutMainOeuvre;  // Total incluant TVA spécifique de chaque composant

        System.out.printf("3. Coût total avant TVA du projet : %.2f €\n", coutTotalSansTVAProjet);

        // Application de la TVA globale du projet
        double coutTVAProjet = coutTotalSansTVAProjet * (tvaProjet / 100);
        System.out.printf("4. TVA du projet (%.2f%%) : %.2f €\n", tvaProjet, coutTVAProjet);

        // Ajout de la TVA globale au coût total
        double coutTotalAvecTVAProjet = coutTotalSansTVAProjet + coutTVAProjet;

        // Application de la marge bénéficiaire
        double coutMarge = coutTotalAvecTVAProjet * (margeBeneficiaire / 100);
        System.out.printf("5. Marge bénéficiaire (%.2f%%) : %.2f €\n", margeBeneficiaire, coutMarge);

        // Ajout de la marge bénéficiaire
        if (margeBeneficiaire > 0) {
            coutTotalAvecTVAProjet += coutMarge;
        }

        System.out.printf("**Coût total final du projet avec TVA et marge : %.2f €**\n", coutTotalAvecTVAProjet);

        // Mise à jour du coût total dans le projet
        projet.setCoutTotal(coutTotalAvecTVAProjet);
    }

    public void coutProjet() throws SQLException {
        System.out.print("Entrez l'ID du projet pour calculer le coût: ");
        int projetId = scanner.nextInt();
        scanner.nextLine();

        // Fetch the project by ID
        Projet projet = projetService.getProjectById(projetId);
        if (projet == null) {
            System.out.println("Projet non trouvé.");
            return;
        }

        System.out.println("\n--- Détails du Projet ---");
        System.out.printf("Nom du projet : %s\n", projet.getNomProjet());
        System.out.printf("Surface : %.2f m²\n", projet.getSurface());

        // Get all materials and calculate total cost
        Collection<Materiel> materiaux = materielService.getAllMaterielByProjectId(projet);
        double coutMateriaux = coutService.totalCostMateriel(materiaux);
        System.out.println("\n--- Coûts des Matériaux ---");
        for (Materiel materiel : materiaux) {
            double cout = coutService.coutMateriel(materiel);
            double tvaMateriel = materiel.getTauxTVA();
            cout *= (1 + tvaMateriel / 100); // Apply TVA
            System.out.printf("- %s : %.2f € (TVA incluse)\n", materiel.getNom(), cout);
        }
        System.out.printf("**Coût total des matériaux : %.2f €**\n", coutMateriaux);

        // Get all labor and calculate total cost
        Collection<MainOeuvre> mainOeuvres = mainOeuvreService.getAllMainOeuvre(projet);
        double coutMainOeuvre = coutService.totalCostMainOeuvre(mainOeuvres);
        System.out.println("\n--- Coûts de la Main-d'œuvre ---");
        for (MainOeuvre mo : mainOeuvres) {
            double cout = coutService.coutMainOeuvre(mo);
            double tvaMainOeuvre = mo.getTauxTVA();
            cout *= (1 + tvaMainOeuvre / 100); // Apply TVA
            System.out.printf("- %s : %.2f € (TVA incluse)\n", mo.getNom(), cout);
        }
        System.out.printf("**Coût total de la main-d'œuvre : %.2f €**\n", coutMainOeuvre);

        // Calculate total cost
        double coutTotal = coutMateriaux + coutMainOeuvre;
        System.out.printf("**Coût total du projet : %.2f €**\n", coutTotal);
    }

    public HashMap<Integer,Projet> DisplayallProjects() throws SQLException {
        HashMap<Integer,Projet> projets =projetService.getAllProjets();
        if(projets.isEmpty()){
            System.out.println("Aucun projets n'est trouvé");
        }else{
            projets.values().stream().forEach(projet -> {
                System.out.println(" Nom du Projet: " + projet.getNomProjet());
            });
        }
        return projets;
    }

    public HashMap<Integer,Projet> SearchProject() throws SQLException {
        HashMap<Integer,Projet> projets =projetService.getAllProjets();

        System.out.println("Entrer le nom du projet que vous souhaitez chercher: ");
        String nomProjet=scanner.nextLine();

        HashMap<Integer, Projet> filteredProjets = (HashMap<Integer, Projet>) projets.values().stream()
                .filter(projet -> projet.getNomProjet().toLowerCase().contains(nomProjet.toLowerCase()))
                .collect(Collectors.toMap(Projet::getId, projet -> projet));

        if(filteredProjets.isEmpty()){
            System.out.println("Aucun projet trouvé avec le nom: " + nomProjet);
        }else{
            filteredProjets.values().forEach(projet ->
                    System.out.println( "Nom du projet: " + projet.getNomProjet() +", Surface du projet: "+projet.getSurface() +
                            ", Etat du projet: "+ projet.getEtatProjet() +"\n")
            );
        }
        return filteredProjets;
    }







}
