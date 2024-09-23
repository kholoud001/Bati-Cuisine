package DAO.implementations;

import DAO.interfaces.DevisDAO;
import DAO.interfaces.ProjetDAO;
import config.DatabaseConnection;
import entities.Devis;
import entities.Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DevisDAOImp implements DevisDAO {

    private Connection connection;

    public DevisDAOImp(Connection connection) throws SQLException {
        this.connection= connection;
    }

    public void add(Devis devis) throws SQLException {
        String query="INSERT INTO devis(montantEstime,dateEmission,dateValidite,accepte,projet_id) VALUES(?,?,?,?,?)";

        try(PreparedStatement statement=connection.prepareStatement(query)){
            statement.setDouble(1, devis.getMontantEstime());
            statement.setDate(2,java.sql.Date.valueOf(devis.getDateEmission()));
            statement.setDate(3,java.sql.Date.valueOf(devis.getDateValidite()));
            statement.setBoolean(4, devis.isAccepte());
            statement.setInt(5,devis.getProjet().getId());
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void update(Devis devis) throws SQLException {
        String query = "UPDATE devis SET montantEstime = ?, dateEmission = ?, dateValidite = ?, accepte = ?, projet_id = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, devis.getMontantEstime());
            statement.setDate(2, java.sql.Date.valueOf(devis.getDateEmission()));
            statement.setDate(3, java.sql.Date.valueOf(devis.getDateValidite()));
            statement.setBoolean(4, devis.isAccepte());
            statement.setInt(5, devis.getProjet().getId());
            statement.setInt(6, devis.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du devis: " + e.getMessage());
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM devis WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du devis: " + e.getMessage());
        }
    }

    public HashMap<Integer, Devis> getAll() throws SQLException {
        String query = "SELECT * FROM devis";
        HashMap<Integer, Devis> devisMap = new HashMap<>();
        ProjetDAO projetDAO = new ProjetDAOImp(connection);

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Projet projet = projetDAO.getProjetById(resultSet.getInt("projet_id"));

                Devis devis = new Devis(
                        resultSet.getInt("id"),
                        resultSet.getDouble("montantEstime"),
                        resultSet.getDate("dateEmission").toLocalDate(),
                        resultSet.getDate("dateValidite").toLocalDate(),
                        resultSet.getBoolean("accepte"),
                        projet
                );
                devisMap.put(devis.getId(), devis);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des devis: " + e.getMessage());
        }
        return devisMap;
    }



    public Devis getDevisByProjetId(int projetId) throws SQLException {
        Devis devis = null;
        String query = "SELECT * FROM devis WHERE projet_id = ?";
        ProjetDAO projetDAO = new ProjetDAOImp(connection);

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, projetId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Projet projet = projetDAO.getProjetById(projetId);
                devis = new Devis(
                        resultSet.getInt("id"),
                        resultSet.getDouble("montantEstime"),
                        resultSet.getDate("dateEmission").toLocalDate(),
                        resultSet.getDate("dateValidite").toLocalDate(),
                        resultSet.getBoolean("accepte"),
                        projet
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du devis : " + e.getMessage());
        }
        return devis;
    }


    public void accepterDevis(int devisId) throws SQLException {
        String query = "UPDATE devis SET accepte = TRUE WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, devisId);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Le devis a été accepté avec succès.");
            } else {
                System.out.println("Aucun devis trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'acceptation du devis : " + e.getMessage());
        }
    }


    public void refuserDevis(int devisId) throws SQLException {
        String query = "UPDATE devis SET accepte = FALSE WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, devisId);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Le devis a été refusé avec succès.");
            } else {
                System.out.println("Aucun devis trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du refus du devis : " + e.getMessage());
        }
    }

}
