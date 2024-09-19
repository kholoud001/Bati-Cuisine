package GUI;

import entities.Client;
import entities.Projet;
import enums.EtatProjet;
import services.implementations.ProjetServiceImp;
import services.interfaces.ProjetService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class ProjetGUI {

    private ProjetService projetService;
    private Scanner scanner;

    public ProjetGUI(Scanner scanner) throws SQLException {
        this.projetService=new ProjetServiceImp();
        this.scanner=scanner;
    }

    public void addProjet(Client client) throws SQLException {
        System.out.println("--- Création d'un Nouveau Projet ---");
        System.out.print("Entrez le nom du projet: ");
        String nomProjet = scanner.nextLine();

        System.out.print("Entrez la surface de la cuisine (en m²) : ");
        double surface = scanner.nextDouble();
        scanner.nextLine();


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
       // System.out.print("Enter total cost: ");
        //double coutTotal = scanner.nextDouble();
        double coutTotal=0;
        scanner.nextLine();

        System.out.println("Enter project state (EN_COURS, TERMINE, ANNULE): ");
        String etat = scanner.nextLine();
        EtatProjet etatProjet = EtatProjet.valueOf(etat.toUpperCase());

        Projet projet = new Projet(nomProjet, surface, tvaProjet, margeBeneficiaire, coutTotal, etatProjet, client);
        projetService.createProject(projet);
        System.out.println("Project added successfully.");


    }

}
