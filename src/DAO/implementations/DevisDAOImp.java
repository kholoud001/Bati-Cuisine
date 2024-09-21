package DAO.implementations;

import DAO.interfaces.DevisDAO;
import config.DatabaseConnection;
import entities.Devis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
