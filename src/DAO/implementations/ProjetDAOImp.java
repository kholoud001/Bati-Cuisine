package DAO.implementations;

import DAO.interfaces.ProjetDAO;
import config.DatabaseConnection;
import entities.Projet;
import enums.EtatProjet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjetDAOImp implements ProjetDAO {

    private final Connection connection;

    public ProjetDAOImp() throws SQLException {
        this.connection= DatabaseConnection.getInstance().getConnection();
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
}
