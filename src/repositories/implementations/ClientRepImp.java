package repositories.implementations;

import DAO.implementations.ClientDAOImp;
import DAO.interfaces.ClientDAO;
import entities.Client;
import repositories.interfaces.ClientRep;

import java.sql.SQLException;

public class ClientRepImp implements ClientRep {
    private final ClientDAO clientDAO;

    public ClientRepImp() throws SQLException {
        this.clientDAO = new ClientDAOImp();
    }

    public void save(Client client) throws SQLException {
        clientDAO.add(client);
    }
}
