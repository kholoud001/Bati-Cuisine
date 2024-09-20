package DAO.implementations;

import DAO.interfaces.MaterielDAO;
import config.DatabaseConnection;
import entities.Materiel;
import entities.Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MaterielDAOImp implements MaterielDAO {

    private Connection connection;

    public MaterielDAOImp() throws SQLException {
        this.connection= DatabaseConnection.getInstance().getConnection();
    }

    public Collection<Materiel> getMatrielByProjectId(Projet projet) throws SQLException {
        String query="SELECT * FROM matériaux WHERE projet_id=?";
        Collection<Materiel> materiaux = new ArrayList<Materiel>();

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, projet.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Projet projetFromDb = new Projet(rs.getInt("projet_id"));

                Materiel materiel = new Materiel(
                        rs.getString("nom"),
                        rs.getDouble("tauxTVA"),
                        rs.getString("typeComposant"),
                        projetFromDb,
                        rs.getDouble("cout_unitaire"),
                        rs.getDouble("cout_transport"),
                        rs.getDouble("coefficient_qualite"),
                        rs.getDouble("quantite")
                );
                materiaux.add(materiel);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return materiaux;
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
