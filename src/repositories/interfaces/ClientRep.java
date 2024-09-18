package repositories.interfaces;

import entities.Client;

import java.sql.SQLException;

public interface ClientRep {
    void save(Client client) throws SQLException;
}
