/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Categorie;
import entities.Produit;
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
public class ProduitService implements IProduit<Produit>{
    
    Connection connexion;
    Statement stm;
    
    public ProduitService(){
        connexion = MyDB.getInstance().getConnexion();
    }
    

    
    @Override
       public void ajouterp(Produit p) throws SQLException {
        String req = "INSERT INTO `produit`(nom , prix , description , marque)  "
                + "VALUES (?, ?, ? ,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.setFloat(2, p.getPrix());
        ps.setString(3, p.getMarque());
       
        ps.setString(4, p.getDescription());
        
      
       
        ps.executeUpdate();
    }

    @Override
    public List<Produit> afficherproduit() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String req = "select * from produit p left join categorie c on p.idCategorie = c.idCategorie";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produit p = new Produit(
                    rst.getInt("IdProduit"),
                    new Categorie(rst.getInt("idCategorie"), rst.getString("nom") , rst.getString("description")),
                    
                    rst.getInt("nbetoiles"),
                   
                    //or rst.getInt(1)
                  
                    rst.getString("nom"),
                    rst.getString("description"),
                    rst.getString("marque"),
                    rst.getFloat("prix") ,
                    rst.getString("image") 
            )
                    ;
                    
            produits.add(p);
        }
        return produits;
    }

    @Override
    public void deleteProduit(int idProduit) throws SQLException {
         String req = "delete from produit where idProduit =?";
        PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, idProduit);
            ps.executeUpdate();
    }

    @Override
    public boolean updateProduit(int idProduit,  Produit p) {
        try {

            PreparedStatement pre = connexion.prepareStatement("UPDATE produit SET idProduit = ? ,nom = ? , description= ?,marque = ? , prix= ?  where idProduit= ? ;");
           
           
            pre.setString(1, p.getNom());
            pre.setString(2, p.getDescription());  
            pre.setString(3, p.getMarque());
            pre.setFloat(4, p.getPrix());   
            pre.setInt(5, idProduit);
            if (pre.executeUpdate() != 0) {
                System.out.println(" Produit Updated");
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
    public List<Produit> afficherProduitTriP() throws SQLException {
 List<Produit> produits = new ArrayList<>();
        String req = "select * from produit p join categorie c on p.idCategorie=c.idCategorie order by prix";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produit p = new Produit(
                    rst.getInt("IdProduit"),
                    
                     new Categorie(rst.getInt("idCategorie"), rst.getString("nom") , rst.getString("description")),
                    
                    rst.getInt("nbetoiles"),
                   
                   //or rst.getInt(1)
                  
                    rst.getString("nom"),
                    rst.getString("description"),
                    rst.getString("marque"),
                    rst.getFloat("prix"),
            rst.getString("image") )
                    ;
                    
            produits.add(p);
        }
        return produits;    }

    @Override
    public List<Produit> afficherProduitParCategorie(String categorie) throws SQLException {
List<Produit> produits = new ArrayList<>();
        String req = "select * from produit p join categorie c on p.idCategorie=c.idCategorie where c.nom='" + categorie + "'";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produit p = new Produit(
                    rst.getInt("IdProduit"),
                    
                    new Categorie(rst.getInt("idCategorie"), rst.getString("nom") , rst.getString("description")), 
                   
                    rst.getInt("nbetoiles"),
                   
                   
                  
                    rst.getString("nom"),
                    rst.getString("description"),
                    rst.getString("marque"),
                    rst.getFloat("prix"),
            rst.getString("image") )
                    ;
                    
            produits.add(p);
        }
        return produits;     }

    @Override
    public List<Produit> RechercheProduit(String nom) throws SQLException {
        List<Produit> produits = new ArrayList<>();
        PreparedStatement pre = connexion.prepareStatement("select * from produit p join categorie c on p.idCategorie=c.idCategorie where c.nom like ? or p.nom like ?");
          
             pre.setString(1, "%" + nom+ "%");
             pre.setString(2, "%" + nom+ "%");
       
        //ensemble de resultat
        ResultSet rst = pre.executeQuery();
        

        while (rst.next()) {
            Produit p = new Produit(
                    rst.getInt("IdProduit"),
                    
                     new Categorie(rst.getInt("idCategorie"), rst.getString("nom") , rst.getString("description")),
                    
                    rst.getInt("nbetoiles"),
                   
                 //or rst.getInt(1)
                  
                    rst.getString("nom"),
                    rst.getString("description"),
                    rst.getString("marque"),
                    rst.getFloat("prix") , 
            rst.getString("image") )
                    ;
                    
            produits.add(p);
        }
        return produits;    }

    @Override
    public List<Produit> addichierProduitParNote() throws SQLException {
List<Produit> produits = new ArrayList<>();
        String req = "select * from produit order by nbEtoiles desc";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Produit p = new Produit(
                    rst.getInt("IdProduit"),
                    
                     new Categorie(rst.getInt("idCategorie"), rst.getString("nom") , rst.getString("description")),
                    
                    rst.getInt("nbetoiles"),
                   
                
                  
                    rst.getString("nom"),
                    rst.getString("description"),
                    rst.getString("marque"),
                    rst.getFloat("prix") ,
              rst.getString("image") )
                    ;
                    
            produits.add(p);
        }
        return produits;    }

 
    
    
}
