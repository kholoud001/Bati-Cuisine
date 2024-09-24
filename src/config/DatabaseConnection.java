package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/BatiCuisine";
    private String user = "postgres";
    private String password = "action";

    private DatabaseConnection() {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            System.out.println("Database Connection Creation Failed : " + exception.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }





}
