package GUI;

import entities.Devis;
import entities.Projet;
import services.interfaces.DevisService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DevisGUI {

    private  DevisService devisService;
    private  Scanner scanner;

    private  DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public DevisGUI(DevisService devisService,Scanner scanner) {
        this.devisService = devisService;
        this.scanner = scanner;
    }

    public  void createDevis(Projet projet){
        System.out.println("--- Enregistrement du Devis ---\n");

        double montantEstime = projet.getCoutTotal();
        LocalDate date_validite;
        LocalDate date_emission;


        while (true) {
            System.out.println("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
            date_emission = LocalDate.parse(scanner.next(), formatter);
            if (!date_emission.isBefore(LocalDate.now())) {
                break;
            } else {
                System.out.println("La date d'émission doit être à partir d'aujourd'hui ou ultérieure. Veuillez réessayer.");
            }
        }

        while (true) {
            System.out.println("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
            date_validite = LocalDate.parse(scanner.next(), formatter);
            if (!date_validite.isBefore(LocalDate.now())) {
                break;
            } else {
                System.out.println("La date de validité doit être à partir d'aujourd'hui ou ultérieure. Veuillez réessayer.");
            }
        }

        scanner.nextLine();

        System.out.print("Souhaitez-vous enregistrer le devis ? (y/n) : ");
        String confirmation = scanner.nextLine().toLowerCase();
        if (confirmation.equals("y")) {
            Devis devis = new Devis(0, montantEstime, date_emission, date_validite, false, projet);
            try {
                devisService.saveDevis(devis);
                System.out.println("Devis enregistré avec succès !");
            } catch (Exception e) {
                System.out.println("Erreur lors de l'enregistrement du devis : " + e.getMessage());
            }
        } else {
            System.out.println("Enregistrement du devis annulé.");
        }
    }

}
