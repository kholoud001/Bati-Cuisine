package DAO.implementations;

import DAO.interfaces.MaterielDAO;
import config.DatabaseConnection;
import entities.Materiel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaterielDAOImp implements MaterielDAO {

    private Connection connection;

    public MaterielDAOImp() throws SQLException {
        this.connection= DatabaseConnection.getInstance().getConnection();
    }

    public void add(Materiel materiel) throws SQLException {
        String query = "INSERT INTO matériaux (nom, tauxTVA, typeComposant, projet_id, " +
                "cout_unitaire, cout_transport, coefficient_qualite, quantite) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, materiel.getNom());
            ps.setDouble(2, materiel.getTauxTVA());
            ps.setString(3, materiel.getTypeComposant());
            ps.setInt(4,materiel.getProjet().getId());
            ps.setDouble(5, materiel.getCoutUnitaire());
            ps.setDouble(6, materiel.getCoutTransport());
            ps.setDouble(7, materiel.getCoefficientQualite());
            ps.setDouble(8, materiel.getQuantite());
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
