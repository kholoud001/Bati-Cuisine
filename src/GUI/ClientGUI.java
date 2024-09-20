package GUI;

import entities.Client;
import services.implementations.ClientServiceImp;
import services.implementations.MainOeuvreServiceImp;
import services.implementations.MaterielServiceImp;
import services.implementations.ProjetServiceImp;
import services.interfaces.ClientService;
import services.interfaces.MainOeuvreService;
import services.interfaces.MaterielService;
import services.interfaces.ProjetService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class ClientGUI {
    private final ClientService clientService;
    private Scanner scanner;
    ProjetService projetService= new ProjetServiceImp();

    ProjetGUI projetGUI= new ProjetGUI(scanner,projetService);


    public ClientGUI(Scanner scanner,ClientService clientService) throws SQLException {
        this.clientService=clientService;
        this.scanner=scanner;
    }

    public void displayMenuClient() throws SQLException {
        boolean exit=false;

        while(!exit){
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.println("3. Revenir au menu principal");
        System.out.print("Choisissez une option : ");

        int option=scanner.nextInt();
        scanner.nextLine();
        switch(option){
            case 1:
                searchClientByName();
                break;
            case 2:
                addClient();
                break;
            case 3:
                exit=true;
                break;
            default:
                System.out.println("Choix invalid. SVP recommencer.");
        }
        }

    }

    public void addClient() throws SQLException {

        System.out.println("******* Add client ******* \n");
        System.out.println("Enter client name: ");
        String name = scanner.nextLine();

        System.out.println("Enter client address: ");
        String address = scanner.nextLine();

        System.out.println("Enter client telephone: ");
        String telephone = scanner.nextLine();

        System.out.println("Is the client a professional? (true/false): ");
        boolean estProfessionel = scanner.nextBoolean();

        Client client = new Client(name, address, telephone,estProfessionel);
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


    public void searchClientByName() throws SQLException {
        System.out.println("--- Recherche de client existant ---");
        System.out.print("Entrez le nom du client : ");
        String clientName = scanner.nextLine();

        HashMap<Integer, Client> clientHashMap = clientService.getClientByName(clientName); // Ensure the method name is consistent
        if (clientHashMap.isEmpty()) {
            System.out.println("Client non trouvé");
            return;

        } else {
            System.out.println("Client trouvé !");
            clientHashMap.forEach((id, client) -> {
                System.out.println("Nom: " + client.getName());
                System.out.println("Adresse: " + client.getAddress());
                System.out.println("Numéro de téléphone: " + client.getTelephone());
            });

            System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
            String response=scanner.nextLine().toLowerCase();

            if(response.equals("y")){

                int keys = clientHashMap.keySet().stream().findFirst().get();
                System.out.println("keys "+keys);
                Client selectedClient = clientHashMap.get(keys);

                projetGUI.addProjet(selectedClient);
            }else{
                System.out.println("--- Retour au menu précedent ---");
                return;
            }
        }
    }
}
