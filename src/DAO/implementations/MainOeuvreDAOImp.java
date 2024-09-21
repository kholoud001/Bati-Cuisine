package DAO.implementations;

import DAO.interfaces.MainOeuvreDAO;
import config.DatabaseConnection;
import entities.MainOeuvre;
import entities.Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MainOeuvreDAOImp implements MainOeuvreDAO {

    private Connection connection;

    public MainOeuvreDAOImp(Connection connection) throws SQLException {
        this.connection= connection;

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

    public Collection<MainOeuvre> getMainOeuvreByProjectId(Projet projet) throws SQLException {
           String query="SELECT * FROM main_oeuvres WHERE projet_id=?";
           Collection<MainOeuvre> mains =new ArrayList<MainOeuvre>();

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, projet.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Projet projetFromDb= new Projet(rs.getInt("projet_id"));

                MainOeuvre main= new MainOeuvre(
                        rs.getString("nom"),
                        rs.getDouble("tauxTVA"),
                        rs.getString("typeComposant"),
                        projetFromDb,
                        rs.getDouble("taux_horaire"),
                        rs.getDouble("heures_travail"),
                        rs.getDouble("productivite_ouvrier")
                );
                mains.add(main);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       return mains;
    }


}
