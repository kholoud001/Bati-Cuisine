package services.implementations;

import entities.Client;
import repositories.implementations.ClientRepImp;
import repositories.interfaces.ClientRep;
import services.interfaces.ClientService;

import java.sql.SQLException;

public class ClientServiceImp implements ClientService {

    private final ClientRep clientRep;

    public ClientServiceImp() throws SQLException {
        this.clientRep = new ClientRepImp();
    }

    public void createClient(Client client) throws SQLException {
        clientRep.save(client);
    }
}
