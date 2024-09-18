package GUI;

import config.DatabaseConnection;
import entities.Client;
import services.implementations.ClientServiceImp;
import services.interfaces.ClientService;

import java.sql.SQLException;
import java.util.Scanner;

public class MainGUI {

    private final ClientService clientService;
    private Scanner scanner;

    public MainGUI(Scanner scanner) throws SQLException {
        this.clientService=new ClientServiceImp();
        this.scanner=scanner;

    }

    public void ConnectionToDB() throws SQLException {
        DatabaseConnection database= DatabaseConnection.getInstance();
    }

    public void addClient() throws SQLException {

        System.out.println("******* Add client *******");
        System.out.println("Enter client name: ");
        String name = scanner.nextLine();

        System.out.println("Enter client telephone: ");
        String telephone = scanner.nextLine();

        System.out.println("Is the client a professional? (true/false): ");
        boolean estProfessionel = scanner.nextBoolean();

        Client client = new Client(name, telephone, estProfessionel);
        clientService.createClient(client);
        System.out.println("Client added successfully.");
    }

}
