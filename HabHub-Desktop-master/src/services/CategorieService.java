/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import static com.sun.jmx.remote.internal.IIOPHelper.connect;
import entities.Categorie;
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
public class CategorieService implements Icategorie<Categorie> {
    
      Connection connexion;
      Statement stm;

    public CategorieService() {
        connexion = MyDB.getInstance().getConnexion();
    }
      
      

    @Override
    public void ajouterc(Categorie c) throws SQLException {
        
          String req = "INSERT INTO `categorie` (  nom, description)  "
                + "VALUES ( ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, c.getNom());
        ps.setString(2, c.getDescription());
       
        ps.executeUpdate();
    }
    

    @Override
    public List<Categorie> affichercategorie() throws SQLException {
         List<Categorie> categories = new ArrayList<>();
        String req = "select * from categorie";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Categorie c = new Categorie(rst.getInt("idCategorie"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("description")
                    );
                    
            categories.add(c);
        }
        return categories;
    }
    
    
 

    @Override
    public void deleteCategorie(int idCatgeorie) throws SQLException {
      String req = "delete from categorie where idCategorie =?";
        PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, idCatgeorie);
            ps.executeUpdate();
    }

    @Override
    public boolean updateCategorie(int idCategorie, String nom, String description) {
       try {

            PreparedStatement pre = connexion.prepareStatement("UPDATE categorie SET nom = ? , description= ?  where idCategorie= ? ;");
           
            pre.setString(1, nom);
            pre.setString(2, description);   
            pre.setInt(3, idCategorie);
            if (pre.executeUpdate() != 0) {
                System.out.println(" Categorie Updated");
                 } else {
                System.out.println("not Updated");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
}
