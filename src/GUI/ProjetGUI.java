package GUI;

import entities.Client;
import entities.Projet;
import enums.EtatProjet;
import services.implementations.ProjetServiceImp;
import services.interfaces.ProjetService;

import java.sql.SQLException;
import java.util.Scanner;

public class ProjetGUI {

    private ProjetService projetService;
    private Scanner scanner;

    public ProjetGUI(Scanner scanner) throws SQLException {
        this.projetService=new ProjetServiceImp();
        this.scanner=scanner;
    }

    public void addProjet() throws SQLException {
        System.out.println("***** Add Project ******");
        System.out.println("Enter projet name: ");
        String nomProjet=scanner.nextLine();

        System.out.print("Enter profit margin: ");
        double margeBeneficiaire = scanner.nextDouble();

        System.out.print("Enter total cost: ");
        double coutTotal = scanner.nextDouble();

        scanner.nextLine();

        System.out.println("Enter project state (EN_COURS, TERMINE, ANNULE): ");
        String etat = scanner.nextLine();
        EtatProjet etatProjet = EtatProjet.valueOf(etat.toUpperCase());

        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();

        Client client = new Client(clientId, "SomeName", "0699298965", true);

        Projet projet = new Projet(nomProjet, margeBeneficiaire, coutTotal, etatProjet, client);

        projetService.createProject(projet);

        System.out.println("Project added successfully.");

    }

}
