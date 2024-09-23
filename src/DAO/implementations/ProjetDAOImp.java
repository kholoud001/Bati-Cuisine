package DAO.implementations;

import DAO.interfaces.ProjetDAO;
import config.DatabaseConnection;
import entities.Client;
import entities.Projet;
import enums.EtatProjet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProjetDAOImp implements ProjetDAO {

    private final Connection connection;

    public ProjetDAOImp(Connection connection) throws SQLException {
        this.connection= connection;
    }

    public Projet add(Projet projet) throws SQLException {
        String query = "INSERT INTO projets (nomprojet, surface, tvaProjet, margebeneficiaire, couttotal, etatprojet, client_id) " +
                "VALUES (?, ?, ?, ?, ?, ?::etat_projet_enum, ?) RETURNING *";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the parameters for the PreparedStatement
            stmt.setString(1, projet.getNomProjet());
            stmt.setDouble(2, projet.getSurface());
            stmt.setDouble(3, projet.getTvaProjet());
            stmt.setDouble(4, projet.getMargeBeneficiaire());
            stmt.setDouble(5, projet.getCoutTotal());
            stmt.setString(6, projet.getEtatProjet().toString()); // Convert enum to string
            stmt.setInt(7, projet.getClient().getId());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Projet newProjet= new Projet(
                        rs.getInt("id"),
                        rs.getString("nomprojet"),
                        rs.getDouble("surface"),
                        rs.getDouble("tvaprojet"),
                        rs.getDouble("margebeneficiaire"),
                        rs.getDouble("couttotal"),
                        EtatProjet.valueOf(rs.getString("etatprojet")),
                        projet.getClient()
                );
                return newProjet;
            }
        } catch (SQLException e) {
            throw new SQLException("Error adding new project: " + e.getMessage(), e);
        }

        return null;
    }

    public void updateProject(Projet projet) throws SQLException {
        String query = "UPDATE projets SET nomProjet = ?, surface = ?, tvaProjet = ?, margebeneficiaire = ?, couttotal = ?, etatprojet = ?::etat_projet_enum WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the parameters for the PreparedStatement
            statement.setString(1, projet.getNomProjet());
            statement.setDouble(2, projet.getSurface());
            statement.setDouble(3, projet.getTvaProjet());
            statement.setDouble(4, projet.getMargeBeneficiaire());
            statement.setDouble(5, projet.getCoutTotal());
            statement.setString(6, projet.getEtatProjet().toString());
            statement.setInt(7, projet.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error updating the project: " + e.getMessage(), e);
        }
    }


    public Projet getProjetById(int id) throws SQLException {
        Projet projet = null;
        String query = "SELECT * FROM projets WHERE id = ?";

        // Initialize clientDAOImp (assuming you have access to the required connection)
        ClientDAOImp clientDAOImp = new ClientDAOImp(connection); // Pass the connection if required

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                // Fetch the client using the client ID from the project result set
                int clientId = rs.getInt("client_id");
                Optional<Client> optionalClient = clientDAOImp.getById(clientId);  // Now clientDAOImp is initialized

                if (optionalClient.isPresent()) {
                    Client client = optionalClient.get();

                    projet = new Projet(
                            rs.getInt("id"),
                            rs.getString("nomprojet"),
                            rs.getDouble("surface"),
                            rs.getDouble("tvaprojet"),
                            rs.getDouble("margebeneficiaire"),
                            rs.getDouble("couttotal"),
                            EtatProjet.valueOf(rs.getString("etatprojet")),
                            client
                    );
                } else {
                    System.out.println("Client not found for project.");
                }
            }
        }

        return projet;
    }

}
