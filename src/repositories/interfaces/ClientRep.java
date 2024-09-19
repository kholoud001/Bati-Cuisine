package repositories.interfaces;

import entities.Client;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public interface ClientRep {
    void save(Client client) throws SQLException;
    Optional<Client> findById(int id) throws SQLException;
    HashMap<Integer, Client> findByName(String name) throws SQLException;
}
