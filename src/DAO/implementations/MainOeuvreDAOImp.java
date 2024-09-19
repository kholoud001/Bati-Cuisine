package DAO.implementations;

import DAO.interfaces.MainOeuvreDAO;
import config.DatabaseConnection;
import entities.MainOeuvre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainOeuvreDAOImp implements MainOeuvreDAO {

    private Connection connection;

    public MainOeuvreDAOImp() throws SQLException {
        this.connection= DatabaseConnection.getInstance().getConnection();
    }

    public void add(MainOeuvre mainOeuvre) throws SQLException {
        String query = "INSERT INTO main_oeuvres (nom, tauxTVA, typeComposant, projet_id, " +
                "taux_horaire, heures_travail, productivite_ouvrier) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, mainOeuvre.getNom());
            ps.setDouble(2, mainOeuvre.getTauxTVA());
            ps.setString(3, mainOeuvre.getTypeComposant());
            ps.setInt(4,mainOeuvre.getProjet().getId());
            ps.setDouble(5, mainOeuvre.getTauxHoraire());
            ps.setDouble(6, mainOeuvre.getHeuresTravail());
            ps.setDouble(7, mainOeuvre.getProductiviteOuvrier());
            ps.executeUpdate();

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
