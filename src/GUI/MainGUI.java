package GUI;

import config.DatabaseConnection;
import entities.Client;
import services.implementations.ClientServiceImp;
import services.interfaces.ClientService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class MainGUI {


    private Scanner scanner;
    private ClientService clientService;
    ClientGUI clientGUI = new ClientGUI(scanner,clientService);


    public MainGUI(Scanner scanner, ClientService clientService) throws SQLException {
        this.scanner=scanner;
        this.clientService=clientService;
    }

    public void ConnectionToDB() throws SQLException {
        DatabaseConnection database= DatabaseConnection.getInstance();
    }

    public void displayMenu() throws SQLException {
        boolean exit = false;
        while(!exit){
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");

            String choice = scanner.nextLine();
            switch(choice){
                case "1":
                    clientGUI.displayMenuClient();
                    break;
                case "2":
                     break;
                case "3":
                     break;
                case "4":
                    exit =true;
                    break;
                default:
                    System.out.println("Choix invalid. SVP recommencer.");
            }

        }

    }





}
