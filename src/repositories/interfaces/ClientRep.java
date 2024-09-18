package repositories.interfaces;

import entities.Client;

import java.sql.SQLException;
import java.util.Optional;

public interface ClientRep {
    void save(Client client) throws SQLException;
    Optional<Client> findById(int id) throws SQLException;
}
