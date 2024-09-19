package services.implementations;

import entities.Client;
import repositories.implementations.ClientRepImp;
import repositories.interfaces.ClientRep;
import services.interfaces.ClientService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class ClientServiceImp implements ClientService {

    private final ClientRep clientRep;

    public ClientServiceImp() throws SQLException {
        this.clientRep = new ClientRepImp();
    }

    public void createClient(Client client) throws SQLException {
        clientRep.save(client);
    }

    public Optional<Client> getClientById(int id) throws SQLException {
        return clientRep.findById(id);
    }

    public HashMap<Integer, Client> getClientByName(String name) throws SQLException{
        return clientRep.findByName(name);
    }
}
