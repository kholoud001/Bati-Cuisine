import GUI.MainGUI;
import config.DatabaseConnection;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        MainGUI mainGUI = new MainGUI();
        //mainGUI.ConnectionToDB();
        mainGUI.addClient();


    }
}