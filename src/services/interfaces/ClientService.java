package services.interfaces;

import entities.Client;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public interface ClientService {
    void createClient(Client client) throws SQLException;
    Optional<Client> getClientById(int id) throws SQLException;
    HashMap<Integer, Client> getClientByName(String name) throws SQLException;

}
