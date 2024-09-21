import DAO.implementations.ClientDAOImp;
import DAO.implementations.MainOeuvreDAOImp;
import DAO.implementations.MaterielDAOImp;
import DAO.implementations.ProjetDAOImp;
import DAO.interfaces.ClientDAO;
import DAO.interfaces.MainOeuvreDAO;
import DAO.interfaces.MaterielDAO;
import DAO.interfaces.ProjetDAO;
import GUI.ClientGUI;
import GUI.ComposantGUI;
import GUI.MainGUI;
import GUI.ProjetGUI;
import config.DatabaseConnection;
import repositories.implementations.ClientRepImp;
import repositories.implementations.MainOeuvreRepImp;
import repositories.implementations.MaterielRepImp;
import repositories.implementations.ProjetRepImp;
import repositories.interfaces.ClientRep;
import repositories.interfaces.MainOeuvreRep;
import repositories.interfaces.MaterielRep;
import repositories.interfaces.ProjetRep;
import services.implementations.*;
import services.interfaces.*;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        MaterielDAO materielDAO= new MaterielDAOImp();
        MaterielRep materielRep = new MaterielRepImp(materielDAO);
        MaterielService materielService= new MaterielServiceImp(materielRep);
        MainOeuvreDAO mainOeuvreDAO= new MainOeuvreDAOImp();
        MainOeuvreRep mainOeuvreRep = new MainOeuvreRepImp(mainOeuvreDAO);
        MainOeuvreService mainOeuvreService= new MainOeuvreServiceImp(mainOeuvreRep);
        ComposantGUI composantGUI = new ComposantGUI(sc,mainOeuvreService,materielService);
        CoutService coutService=new CoutServiceImp();

        ProjetDAO projetDAO=new ProjetDAOImp();
        ProjetRep projetRep = new ProjetRepImp(projetDAO);
        ProjetService projetService= new ProjetServiceImp(projetRep);
        ProjetGUI projetGUI= new ProjetGUI(sc,projetService,composantGUI,materielService,mainOeuvreService,coutService);

        ClientDAO clientDAO = new ClientDAOImp();
        ClientRep clientRep = new ClientRepImp(clientDAO);
        ClientService clientService = new ClientServiceImp(clientRep);
        ClientGUI clientGUI= new ClientGUI(sc,clientService,projetGUI);

        MainGUI mainGUI = new MainGUI(sc,clientGUI);
        //mainGUI.ConnectionToDB();
        mainGUI.displayMenu();
        sc.close();




    }
}