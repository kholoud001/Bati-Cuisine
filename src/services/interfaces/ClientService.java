package services.interfaces;

import entities.Client;

import java.sql.SQLException;

public interface ClientService {
    void createClient(Client client) throws SQLException;

}
