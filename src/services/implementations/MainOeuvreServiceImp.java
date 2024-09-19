package services.implementations;

import entities.MainOeuvre;
import repositories.implementations.MainOeuvreRepImp;
import repositories.interfaces.MainOeuvreRep;
import services.interfaces.MainOeuvreService;

import java.sql.SQLException;

public class MainOeuvreServiceImp implements MainOeuvreService {

    private MainOeuvreRep mainOeuvreRep;

    public MainOeuvreServiceImp() throws SQLException {
        this.mainOeuvreRep=new MainOeuvreRepImp();
    }

    public void createMainOeuvre(MainOeuvre mainOeuvre) throws SQLException {
        mainOeuvreRep.save(mainOeuvre);
    }
}
