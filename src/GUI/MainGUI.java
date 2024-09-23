package GUI;

import config.DatabaseConnection;

import services.interfaces.ClientService;

import java.sql.SQLException;
import java.util.Scanner;

public class MainGUI {


    private Scanner scanner;
    private ClientGUI clientGUI;
    private ProjetGUI projetGUI;

    public MainGUI(Scanner scanner, ClientGUI clientGUI, ProjetGUI projetGUI) throws SQLException {
        this.scanner=scanner;
        this.clientGUI = clientGUI;
        this.projetGUI = projetGUI;

    }

    public void displayMenu() throws Exception {
        boolean exit = false;
        while(!exit){
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
           // System.out.println("4. Chercher un projet");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");

            String choice = scanner.nextLine();
            switch(choice){
                case "1":
                    clientGUI.displayMenuClient();
                    break;
                case "2":
                    projetGUI.DisplayallProjects();
                    break;
                case "3":
                    projetGUI.coutProjet();
                    break;
//                case "4":
//                    projetGUI.SearchProject();
//                    break;
                case "0":
                    exit =true;
                    break;
                default:
                    System.out.println("Choix invalid. SVP recommencer.");
            }

        }

    }


    public void ConnectionToDB() throws SQLException {
        DatabaseConnection database= DatabaseConnection.getInstance();
    }






}
