package DAO.interfaces;

import entities.Client;

import java.sql.SQLException;

public interface ClientDAO {
   void add(Client client) throws SQLException;
}
