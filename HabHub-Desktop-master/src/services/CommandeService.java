/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import entities.Commande;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author asus
 */
public class CommandeService implements ICommande<Commande>{
    
    Connection connexion;
      Statement stm;

    public CommandeService() {
        connexion = MyDB.getInstance().getConnexion();
    }
      
      

    @Override
    public void ajouterCom(Commande com) throws SQLException {
         String req = "INSERT INTO `commande` (  idUtilisateur, dateCommande)  "
                + "VALUES ( ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, com.getIdUtilisateur());
       // java.sql.Date sqlDatePublication = new java.sql.Date( com.getDateCommande().getTime() ); 
        java.sql.Date sqlDatePublication = new java.sql.Date( com.getDateCommande().getTime() ); 
            ps.setDate(2,sqlDatePublication);
     
       
        ps.executeUpdate();
    }

    @Override
    public List<Commande> affichercommande() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String req = "select * from commande";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Commande com = new Commande(rst.getInt("idCommande"),//or rst.getInt(1)
                    rst.getInt("idUtilisateur"),
                    rst.getDate("dateCommande")
                    );
                    
            commandes.add(com);
        }
        return commandes;    }

    @Override
    public void deleteCommande(int idCommande) throws SQLException {
        String req = "delete from commande where idCommande =?";
        PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, idCommande);
            ps.executeUpdate();    }

    @Override
    public boolean updateCommande(int idCommande, int idUtilisateur, java.util.Date dateCommande) {
        
        try {

            PreparedStatement pre = connexion.prepareStatement("UPDATE commande SET idUtilisateur = ? , dateCommande= ?  where idCommande= ? ;");
           
            pre.setInt(1, idUtilisateur);
            java.sql.Date sqlDatePublication = new java.sql.Date( dateCommande.getTime() ); 
            pre.setDate(2,sqlDatePublication);  
            pre.setInt(3, idCommande);
            if (pre.executeUpdate() != 0) {
                System.out.println(" Commande Updated");
                 } else {
                System.out.println("not Updated");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Commande> affichercommandeParDate() throws SQLException {
List<Commande> commandes = new ArrayList<>();
        String req = "select * from commande order by dateCommande";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Commande com = new Commande(rst.getInt("idCommande"),//or rst.getInt(1)
                    rst.getInt("idUtilisateur"),
                    rst.getDate("dateCommande")
                    );
                    
            commandes.add(com);
        }
        return commandes;     }
    }
    


  
    
    
    

