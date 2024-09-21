package services.implementations;

import entities.MainOeuvre;
import entities.Projet;
import repositories.implementations.MainOeuvreRepImp;
import repositories.interfaces.MainOeuvreRep;
import services.interfaces.MainOeuvreService;

import java.sql.SQLException;
import java.util.Collection;

public class MainOeuvreServiceImp implements MainOeuvreService {

    private MainOeuvreRep mainOeuvreRep;

    public MainOeuvreServiceImp( MainOeuvreRep mainOeuvreRep) throws SQLException {
        this.mainOeuvreRep= mainOeuvreRep;
    }

    public void createMainOeuvre(MainOeuvre mainOeuvre) throws SQLException {
        mainOeuvreRep.save(mainOeuvre);
    }

    public Collection<MainOeuvre> getAllMainOeuvre(Projet projet) throws SQLException {
        return mainOeuvreRep.getMainByProject(projet);
    }
}
