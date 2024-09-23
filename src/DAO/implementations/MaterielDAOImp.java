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
import java.util.List;

public class MaterielDAOImp implements MaterielDAO {

    private Connection connection;

    public MaterielDAOImp(Connection connection) throws SQLException {
        this.connection= connection;
    }

    /**
     * Récupère tous les matériaux associés à un projet donné.
     *
     *
     * @param projet l'objet `Projet` dont l'identifiant est utilisé pour filtrer
     *               les matériaux.
     * @return une collection de `Materiel` associées au projet, ou une collection
     *         vide si aucun matériau n'est trouvé.
     * @throws SQLException si une erreur se produit lors de l'accès à la
     *                      base de données ou lors de l'exécution de la requête.
     */
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


    /**
     * Ajoute un nouvel objet `Materiel` à la base de données.
     *
     * @param materiel l'objet `Materiel` à ajouter à la base de données.
     * @throws SQLException si une erreur se produit lors de l'accès à la
     *                      base de données ou lors de l'exécution de la requête.
     */
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


//    public List<Materiel> getAll() throws SQLException {
//        String query = "SELECT * FROM matériaux";
//        List<Materiel> materiaux = new ArrayList<>();
//
//        try (PreparedStatement ps = connection.prepareStatement(query);
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                Materiel materiel = new Materiel(
//                        rs.getInt("id"),
//                        rs.getString("nom"),
//                        rs.getDouble("tauxTVA"),
//                        rs.getString("typeComposant"),
//                        new Projet(rs.getInt("projet_id")), // Assurez-vous que l'objet Projet existe avec cet ID
//                        rs.getDouble("cout_unitaire"),
//                        rs.getDouble("cout_transport"),
//                        rs.getDouble("coefficient_qualite"),
//                        rs.getDouble("quantite")
//                );
//                materiaux.add(materiel);
//            }
//        } catch (SQLException e) {
//            System.out.println("Erreur lors de la récupération des matériaux : " + e.getMessage());
//        }
//        return materiaux;
//    }

    public void update(Materiel materiel) throws SQLException {
        String query = "UPDATE matériaux SET nom = ?, tauxTVA = ?, typeComposant = ?, projet_id = ?, " +
                "cout_unitaire = ?, cout_transport = ?, coefficient_qualite = ?, quantite = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, materiel.getNom());
            ps.setDouble(2, materiel.getTauxTVA());
            ps.setString(3, materiel.getTypeComposant());
            ps.setInt(4, materiel.getProjet().getId());
            ps.setDouble(5, materiel.getCoutUnitaire());
            ps.setDouble(6, materiel.getCoutTransport());
            ps.setDouble(7, materiel.getCoefficientQualite());
            ps.setDouble(8, materiel.getQuantite());
            ps.setInt(9, materiel.getId()); // ID du matériel à modifier
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du matériel: " + e.getMessage());
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM matériaux WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Matériel supprimé avec succès.");
            } else {
                System.out.println("Aucun matériel trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du matériel: " + e.getMessage());
        }
    }







}
