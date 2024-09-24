package GUI;


import entities.Client;

import services.interfaces.ClientService;
import utils.ValidateUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class ClientGUI {
    private final ClientService clientService;
    private Scanner scanner;
    ProjetGUI projetGUI;


    public ClientGUI(Scanner scanner,ClientService clientService ,ProjetGUI projetGUI) throws SQLException {
        this.clientService=clientService;
        this.scanner=scanner;
        this.projetGUI= projetGUI;
    }

    public void displayMenuClient() throws SQLException {
        boolean exit=false;

        while(!exit){
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.println("0. Revenir au menu principal");
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
            case 0:
                exit=true;
                break;
            default:
                System.out.println("Choix invalid. SVP recommencer.");
        }
        }

    }

    public void addClient() throws SQLException {

        System.out.println("******* Ajouter client ******* \n");
        String nom;
        while (true) {
            System.out.println("Entrer le nom du client: ");
            nom = scanner.nextLine();
            nom = ValidateUtils.validateNom(nom);
            if (nom != null) {
                break;
            }
        }

        String address;
        while(true) {
            System.out.println("Entrer l'adresse du client: ");
            address= scanner.nextLine();
            if (ValidateUtils.isNotNullOrEmpty(address)) {
                break;
            }
        }


        String telephone;
        while (true) {
            System.out.println("Entrer le numéro de téléphone du client: ");
            telephone = scanner.nextLine();
            if (ValidateUtils.isValidPhone(telephone)) {
                break;
            }
        }

        boolean estProfessionel;
        while (true) {
            System.out.println("Le client est-il un professionnel ? (vrai/faux) : ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("vrai")) {
                estProfessionel = true;
                break;
            } else if (input.equalsIgnoreCase("faux")) {
                estProfessionel = false;
                break;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer 'vrai' ou 'faux'.");
            }
        }

        Client client = new Client(nom, address, telephone,estProfessionel);
        clientService.createClient(client);
        //System.out.println("Client added successfully.");
    }


//    public Optional<Client> viewClient(int clientId) throws SQLException {
//        Optional<Client> clientOptional = clientService.getClientById(clientId);
//        if (clientOptional.isPresent()) {
//            Client client = clientOptional.get();
//            System.out.println("Client ID: " + client.getId());
//            System.out.println("Name: " + client.getName());
//            System.out.println("Telephone: " + client.getTelephone());
//            System.out.println("Professional: " + client.isEstProfessionel());
//        } else {
//            System.out.println("Client not found.");
//        }
//        return clientOptional;
//    }


    public void searchClientByName() throws SQLException {
        System.out.println("--- Recherche de client existant ---");
        System.out.print("Entrez le nom du client : ");
        String clientName = scanner.nextLine();

        HashMap<Integer, Client> clientHashMap = clientService.getClientByName(clientName);
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
