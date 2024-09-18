package GUI;

import config.DatabaseConnection;

import java.sql.SQLException;

public class MainGUI {

    public MainGUI() {

    }

    public void ConnectionToDB() throws SQLException {
        DatabaseConnection database= DatabaseConnection.getInstance();
    }



}
