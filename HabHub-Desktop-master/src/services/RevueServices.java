/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Business;
import entities.Individu;
import entities.Revue;
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
 * @author User
 */
public class RevueServices implements Interface_Revue {
      Connection connect;
      Statement stm;

    public RevueServices() {
      connect=MyDB.getInstance().getConnexion();

    }
        @Override
       public void ajouterRevueBusiness(Revue R)throws SQLException {
        PreparedStatement pre = connect.prepareStatement("INSERT INTO revue (idIndividu,idBusiness,nbEtoiles,commentaire,datePublication)VALUES (?,?,?,?,SYSDATE());");

            pre.setInt(1, R.getIndividu().getIdIndividu());
            pre.setInt(2, R.getBusiness().getIdBusiness());
            pre.setInt(3, R.getNbEtoiles());
            pre.setString(4, R.getCommentaire());   
            pre.executeUpdate();
   }
         public boolean UpdateRevueBusiness(Individu indiv,int idProduit ,int idBusiness,int nbEtoiles, String commentaire,int idRevue) {
            try {

            PreparedStatement pre = connect.prepareStatement("UPDATE revue SET idIndividu = ?,idBusiness = ?, nbEtoiles= ? , commentaire= ?  where idRevue= ? ;");
            pre.setInt(1, indiv.getIdIndividu());
            pre.setInt(2, idBusiness);
            pre.setInt(3, nbEtoiles);
            pre.setString(4, commentaire);   
            pre.setInt(5,idRevue);

            if (pre.executeUpdate() != 0) {
                System.out.println(" Revue Updated");
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
         public boolean deleteRevue(Integer idRevue) throws SQLException {

        PreparedStatement pre = connect.prepareStatement("Delete from revue where idRevue=? ;");
        pre.setInt(1, idRevue);
        if (pre.executeUpdate() != 0) {
            System.out.println("Review Deleted");
            return true;
        }
        System.out.println("id of Review not found");
        return false;

    }

    @Override
    public boolean UpdateRevueBusiness(Individu indiv, int idProduit, int nbEtoiles, String commentaire, int idRevue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public List<Revue> afficherAllRevues() throws SQLException {
        List<Revue> revues = new ArrayList<>();
        String req = "select * from revue where idProduit is NULL ;";
        stm = connect.createStatement();
        ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
            Revue R = new Revue (rst.getInt("idRevue"),
            new Individu(rst.getInt("idIndividu")),
            new Business(rst.getInt("idBusiness")),
            rst.getInt("nbEtoiles"),
            rst.getString("commentaire"));

            revues.add(R);
        }
        return revues;    
    }
    
    //requete jointure affichage
    /*SELECT titre,prenom,nbEtoiles,commentaire from revue join business ON revue.idBusiness=business.idBusiness JOIN individu ON individu.idIndividu=revue.idIndividu WHERE business.idBusiness="9";*/
    
      @Override
        public List<Revue> afficherRevueBusiness(String BusinessTitle) throws SQLException {
        List<Revue> revues = new ArrayList<>();
        PreparedStatement stm = connect.prepareStatement("SELECT * from revue join business ON revue.idBusiness=business.idBusiness JOIN individu ON individu.idIndividu=revue.idIndividu WHERE business.titre=?;");
        stm.setString(1,BusinessTitle);
        //stm.setString(1, business.getTitre());

        ResultSet rst = stm.executeQuery();
            while (rst.next()) {
            Revue R = new Revue (
            rst.getInt("idRevue"),
            new Individu(rst.getInt("idIndividu"),rst.getString("prenom"),rst.getString("nom")),
            new Business(rst.getInt("idBusiness"),rst.getString("titre")),
            rst.getInt("nbEtoiles"),
            rst.getString("commentaire"),
            rst.getDate("datePublication"));

            revues.add(R);
        }
        return revues;    
    }
        
        @Override
        public List<Revue> afficherRevueId(int BusinessId) throws SQLException {
        List<Revue> revues = new ArrayList<>();
        PreparedStatement stm = connect.prepareStatement("SELECT * from revue join business ON revue.idBusiness=business.idBusiness JOIN individu ON individu.idIndividu=revue.idIndividu WHERE business.idBusiness=?;");
        stm.setInt(1,BusinessId);
        //stm.setString(1, business.getTitre());

        ResultSet rst = stm.executeQuery();
            while (rst.next()) {
            Revue R = new Revue (
            rst.getInt("idRevue"),
            new Individu(rst.getInt("idIndividu"),rst.getString("prenom"),rst.getString("nom")),
            new Business(rst.getInt("idBusiness"),rst.getString("titre")),
            rst.getInt("nbEtoiles"),
            rst.getString("commentaire"),
            rst.getDate("datePublication"));

            revues.add(R);
        }
        return revues;    
    }
}
 
