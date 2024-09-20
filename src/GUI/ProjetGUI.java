package GUI;

import entities.Client;
import entities.MainOeuvre;
import entities.Materiel;
import entities.Projet;
import enums.EtatProjet;
import services.implementations.CoutServiceImp;
import services.implementations.MainOeuvreServiceImp;
import services.implementations.MaterielServiceImp;
import services.implementations.ProjetServiceImp;
import services.interfaces.CoutService;
import services.interfaces.MainOeuvreService;
import services.interfaces.MaterielService;
import services.interfaces.ProjetService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

public class ProjetGUI {

    private final ProjetService projetService;
    private Scanner scanner;

    MaterielService materielService= new MaterielServiceImp();
    MainOeuvreService mainOeuvreService= new MainOeuvreServiceImp();
    ComposantGUI composantGUI ;
    CoutService coutService= new CoutServiceImp();

    public ProjetGUI(Scanner scanner, ProjetService projetService) throws SQLException {
        this.projetService=projetService;
        this.scanner=scanner;
        this.composantGUI= new ComposantGUI(scanner,mainOeuvreService,materielService);
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

        composantGUI.addMaterialsLoop(projet);
        //ajout main oeuvre
        composantGUI.addLaborLoop(projet);

        System.out.println("Calcul du coût en cours...");

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
        double coutT= calculCout(projet);
        double coutTotalTVA= coutService.costMateriauTva(coutT,tvaProjet);
        System.out.printf("\n**Coût total des matériaux après TVA : %.2f\n\n",coutTotalTVA);
        
        projetService.updateProject(projet);
        //System.out.println("Project added successfully.");
    }


    public double calculCout(Projet projet) throws SQLException {
        System.out.println("\n--- Résultat du Calcul ---\n");
        System.out.printf("Nom du projet: %s \n", projet.getNomProjet());
        System.out.printf("Client: %s \n", projet.getClient().getName());
        System.out.printf("Adresse du chantier: %s \n", projet.getClient().getAddress());
        System.out.printf("Surface: %.2f m² \n", projet.getSurface());
        System.out.println("\n--- Détail des Coûts ---\n");
        System.out.println("1. Matériaux :");

        Collection<Materiel>  materiau= materielService.getAllMaterielByProjectId(projet);
        double coutTotal= coutService.totalCostMateriel(materiau);

        for(Materiel materiel:materiau){
            double cout = coutService.coutMateriel(materiel);
            System.out.printf("- %s : %.2f  € (quantité: %.2f m², " +
                    "coût unitaire: %.2f €/m²,qualité: %.2f ,transport: %.2f €) \n",
                    materiel.getNom(),cout,materiel.getQuantite(),
                    materiel.getCoutUnitaire(),materiel.getCoefficientQualite(),materiel.getCoutTransport());
        }
        System.out.printf("\n**Coût total des matériaux avant TVA : %.2f\n",coutTotal);
        return coutTotal;



    }





}
