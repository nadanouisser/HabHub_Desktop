/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import entities.Produit;
import entities.panier;
import java.sql.Connection;
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
public class PanierService implements IPanier<panier> {
      Connection connexion;
      Statement stm;

    public PanierService() {
         connexion = MyDB.getInstance().getConnexion();
    }
      

    @Override
    public void ajouterPa(panier pa) throws SQLException {
               String req = "INSERT INTO `panier` (  idProduit, idUtilisateur,quantite)  "
                + "VALUES ( ?, ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, pa.getIdProduit().getIdProduit());
        ps.setInt(2,pa.getIdUtilisateur());
        ps.setInt(3, pa.getQuantite());
     
        ps.executeUpdate();
    }

    @Override
    public List<panier> afficherPanier() throws SQLException {
 List<panier> paniers = new ArrayList<>();
        String req = "select * from panier pa join produit p on pa.idProduit=p.idProduit ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()) {
            Produit P = new Produit(rst.getString("p.nom"),rst.getFloat("p.prix"), rst.getString("Image"));
            panier pa = new panier(
               //or rst.getInt(1)
                    P,
                    rst.getInt("idUtilisateur"),
                    rst.getInt("quantite")
                   
                    );
                    
            paniers.add(pa);
        }
        return paniers;
    }

    @Override
    public void deletePanier(int idPanier) throws SQLException {
        String req = "delete from panier where idPanier =?";
        PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, idPanier);
            ps.executeUpdate();
    }

    @Override
    public boolean updatePanier(int idPanier, int idProduit, int idUtilisateur, int quantite) {
        try{
  PreparedStatement pre = connexion.prepareStatement("UPDATE panier SET idProduit= ? , idUtilisateur= ?, quantite= ?  where idPanier= ? ;");
            pre.setInt(1, idProduit);
             pre.setInt(2, idUtilisateur);
            pre.setInt(3, quantite);   
            pre.setInt(4, idPanier);

            if (pre.executeUpdate() != 0) {
                System.out.println(" panier Updated");
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
    public List<panier> afficheParId(int id) throws SQLException {
             List<panier> paniers = new ArrayList<>();
        String req = "select * from produit p join panier pa on p.idProduit=pa.idProduit where idUtilisateur =? ";
              
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1,id);
       
        //ensemble de resultat
        ResultSet rst = ps.executeQuery();

        while (rst.next()) {
             Produit P = new Produit(rst.getString("p.nom"),rst.getFloat("p.prix") , rst.getString("p.image"));
            panier pa = new panier(
               rst.getInt("idPanier"),
                    P,
                    rst.getInt("idUtilisateur"),
                    rst.getInt("quantite")
                   
                    );
                    
            paniers.add(pa);
        }
        return paniers;

}
    
}