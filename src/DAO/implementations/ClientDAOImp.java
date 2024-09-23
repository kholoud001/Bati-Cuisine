package DAO.implementations;

import DAO.interfaces.ClientDAO;
import config.DatabaseConnection;
import entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientDAOImp implements ClientDAO {

    private final Connection connection;

    public ClientDAOImp(Connection connection) throws SQLException {
        this.connection= connection;
    }

    public void add(Client client) throws SQLException {
        String query = "INSERT INTO clients (nom, adresse, telephone, estprofessionel) VALUES (?, ?, ?,?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, client.getName());
                statement.setString(2,client.getAddress());
                statement.setString(3, client.getTelephone());
                statement.setBoolean(4, client.isEstProfessionel());
                statement.executeUpdate();
                //System.out.println("Client DAO added successfully.");
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
    }




    public Optional<Client> getById(int id) throws SQLException {
        String query = "SELECT * FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("adresse"),
                        resultSet.getString("telephone"),
                        resultSet.getBoolean("estprofessionel")
                );
                return Optional.of(client);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public HashMap<Integer, Client> getByName( String name) throws SQLException {
        String query="SELECT * FROM clients";
        HashMap<Integer,Client> clientHashMap = new HashMap<>();

        try(PreparedStatement stmt= connection.prepareStatement(query)) {
            ResultSet resultSet=stmt.executeQuery();
            List<Client> clientList=new ArrayList<>();

            while (resultSet.next()){
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("adresse"),
                        resultSet.getString("telephone"),
                        resultSet.getBoolean("estprofessionel")
                );
                clientList.add(client);
                clientHashMap= (HashMap<Integer, Client>) clientList.stream()
                        .filter(clientL -> clientL.getName().equalsIgnoreCase(name))
                        .collect(Collectors.toMap(Client::getId,clientL->clientL));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return clientHashMap;
    }


    public HashMap<Integer,Client> getAll() throws SQLException {
        String query = "SELECT * FROM clients";
        HashMap<Integer,Client> clients = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("adresse"),
                        resultSet.getString("telephone"),
                        resultSet.getBoolean("estprofessionel")
                );
                clients.put(client.getId(),client);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des clients : " + e.getMessage());
        }
        return clients;
    }

    public void update(Client client) throws SQLException {
        String query = "UPDATE clients SET nom = ?, adresse = ?, telephone = ?, estprofessionel = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getTelephone());
            statement.setBoolean(4, client.isEstProfessionel());
            statement.setInt(5, client.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Client mis à jour avec succès.");
            } else {
                System.out.println("Aucun client trouvé avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du client : " + e.getMessage());
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM clients WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Client supprimé avec succès.");
            } else {
                System.out.println("Aucun client trouvé avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du client : " + e.getMessage());
        }
    }




}
