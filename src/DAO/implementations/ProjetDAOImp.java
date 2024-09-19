package DAO.implementations;

import DAO.interfaces.ProjetDAO;
import config.DatabaseConnection;
import entities.Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjetDAOImp implements ProjetDAO {

    private final Connection connection;

    public ProjetDAOImp() throws SQLException {
        this.connection= DatabaseConnection.getInstance().getConnection();
    }

    public void add(Projet projet) throws SQLException {
        String query="INSERT INTO projets (nomprojet,surface,tvaProjet, margebeneficiaire, couttotal, etatprojet, client_id) VALUES (?, ?,? ,?, ?, ?::etat_projet_enum, ?)";
        try(PreparedStatement stmt=connection.prepareStatement(query)){
            stmt.setString(1, projet.getNomProjet());
            stmt.setDouble(2,projet.getSurface());
            stmt.setDouble(3,projet.getTvaProjet());
            stmt.setDouble(4,projet.getMargeBeneficiaire());
            stmt.setDouble(5,projet.getCoutTotal());
            stmt.setString(6,projet.getEtatProjet().toString());
            stmt.setInt(7, projet.getClient().getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
