


package services;

import java.sql.SQLException;
import java.util.List;
import entities.Utilisateur;
import entities.Individu;


public interface IUtilisateur <Utilisateur > {
    public boolean login(String login, String mdp)throws SQLException;
     public Utilisateur chercherUtilisateur(String input) throws SQLException;
    public List<Utilisateur> TrierUtilisateur() throws SQLException;
   
   
}
