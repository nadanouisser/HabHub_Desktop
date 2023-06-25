/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author asus
 */
public interface ICommande<Com> {
     public void ajouterCom(Com com) throws SQLException;
    public List<Com> affichercommande() throws SQLException;
    public void deleteCommande(int idCommande) throws SQLException;
   public boolean updateCommande(int idCommande,int idUtilisateur,Date dateCommande) ;
   public List<Com> affichercommandeParDate() throws SQLException;
    
}
