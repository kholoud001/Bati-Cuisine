package GUI;

import config.DatabaseConnection;
import entities.Client;
import services.implementations.ClientServiceImp;
import services.interfaces.ClientService;

import java.sql.SQLException;
import java.util.Optional;
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

    public Optional<Client> viewClient(int clientId) throws SQLException {
        Optional<Client> clientOptional = clientService.getClientById(clientId);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            System.out.println("Client ID: " + client.getId());
            System.out.println("Name: " + client.getName());
            System.out.println("Telephone: " + client.getTelephone());
            System.out.println("Professional: " + client.isEstProfessionel());
        } else {
            System.out.println("Client not found.");
        }
        return clientOptional;
    }


}
