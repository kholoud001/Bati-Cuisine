import GUI.MainGUI;
import config.DatabaseConnection;
import services.implementations.ClientServiceImp;
import services.interfaces.ClientService;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        ClientService clientService = new ClientServiceImp();

        MainGUI mainGUI = new MainGUI(sc,clientService);
        //mainGUI.ConnectionToDB();
        mainGUI.displayMenu();




    }
}