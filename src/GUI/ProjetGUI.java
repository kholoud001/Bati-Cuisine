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

    private final ProjetService projetService;
    private Scanner scanner;

    MaterielService materielService= new MaterielServiceImp();
    MainOeuvreService mainOeuvreService= new MainOeuvreServiceImp();
    ComposantGUI composantGUI = new ComposantGUI(scanner,mainOeuvreService,materielService);

    public ProjetGUI(Scanner scanner, ProjetService projetService) throws SQLException {
        this.projetService=projetService;
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
        composantGUI.addMaterialsLoop(projet);
        //ajout main oeuvre
        composantGUI.addLaborLoop(projet);

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





}
