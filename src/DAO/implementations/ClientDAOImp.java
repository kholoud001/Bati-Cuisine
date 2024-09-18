package DAO.implementations;

import DAO.interfaces.ClientDAO;
import config.DatabaseConnection;
import entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDAOImp implements ClientDAO {

    private final Connection connection;

    public ClientDAOImp() throws SQLException {
        this.connection= DatabaseConnection.getInstance().getConnection();
    }

    public void add(Client client) throws SQLException {
        String query = "INSERT INTO clients (nom, telephone, estprofessionel) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, client.getName());
                statement.setString(2, client.getTelephone());
                statement.setBoolean(3, client.isEstProfessionel());
                statement.executeUpdate();
                System.out.println("Client DAO added successfully.");
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
    }
}
