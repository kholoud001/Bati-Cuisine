import GUI.MainGUI;
import config.DatabaseConnection;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);

        MainGUI mainGUI = new MainGUI(sc);
        //mainGUI.ConnectionToDB();
        mainGUI.displayMenu();




    }
}