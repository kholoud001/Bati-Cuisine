package repositories.implementations;

import DAO.implementations.ClientDAOImp;
import DAO.interfaces.ClientDAO;
import entities.Client;
import repositories.interfaces.ClientRep;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class ClientRepImp implements ClientRep {
    private final ClientDAO clientDAO;

    public ClientRepImp(ClientDAO clientDAO) throws SQLException {
        this.clientDAO = clientDAO;
    }

    public void save(Client client) throws SQLException {
        clientDAO.add(client);
    }

    public Optional<Client> findById(int id) throws SQLException {
       return clientDAO.getById(id);
    }

    public HashMap<Integer, Client> findByName(String name) throws SQLException{
        return clientDAO.getByName(name);

    }
}
