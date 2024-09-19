package DAO.interfaces;

import entities.Client;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public interface ClientDAO {
   void add(Client client) throws SQLException;
   Optional<Client> getById(int id) throws SQLException;
   HashMap<Integer, Client> getByName(String name) throws SQLException;
}
