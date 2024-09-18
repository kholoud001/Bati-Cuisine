import GUI.MainGUI;
import GUI.ProjetGUI;
import config.DatabaseConnection;

import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);

        MainGUI mainGUI = new MainGUI(sc);
        //mainGUI.ConnectionToDB();
       // mainGUI.addClient();
        ProjetGUI projetGUI = new ProjetGUI(sc);
        projetGUI.addProjet();


    }
}