import DAO.implementations.*;
import DAO.interfaces.*;
import GUI.*;
import config.DatabaseConnection;
import repositories.implementations.*;
import repositories.interfaces.*;
import services.implementations.*;
import services.interfaces.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Connection connection = DatabaseConnection.getInstance().getConnection();


        MaterielDAO materielDAO= new MaterielDAOImp(connection);
        MaterielRep materielRep = new MaterielRepImp(materielDAO);
        MaterielService materielService= new MaterielServiceImp(materielRep);
        MainOeuvreDAO mainOeuvreDAO= new MainOeuvreDAOImp(connection);
        MainOeuvreRep mainOeuvreRep = new MainOeuvreRepImp(mainOeuvreDAO);
        MainOeuvreService mainOeuvreService= new MainOeuvreServiceImp(mainOeuvreRep);
        ComposantGUI composantGUI = new ComposantGUI(sc,mainOeuvreService,materielService);
        CoutService coutService=new CoutServiceImp();

        DevisDAO devisDAO =new DevisDAOImp(connection);
        DevisRep devisRep = new DevisRepImp(devisDAO);
        DevisService devisService= new DevisServiceImp(devisRep);
        DevisGUI devisGUI= new DevisGUI(devisService,sc);


        ProjetDAO projetDAO=new ProjetDAOImp(connection);
        ProjetRep projetRep = new ProjetRepImp(projetDAO);
        ProjetService projetService= new ProjetServiceImp(projetRep);
        ProjetGUI projetGUI= new ProjetGUI(sc,projetService,composantGUI,devisGUI,materielService,mainOeuvreService,coutService);

        ClientDAO clientDAO = new ClientDAOImp(connection);
        ClientRep clientRep = new ClientRepImp(clientDAO);
        ClientService clientService = new ClientServiceImp(clientRep);
        ClientGUI clientGUI= new ClientGUI(sc,clientService,projetGUI);

        MainGUI mainGUI = new MainGUI(sc,clientGUI);
        //mainGUI.ConnectionToDB();
        mainGUI.displayMenu();
        sc.close();




    }
}