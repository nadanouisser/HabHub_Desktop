/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Individu;
import entities.Utilisateur;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;
import utils.Statics;

/**
 *
 * @author NADA_USER
 */
public class UserIndividuServices implements IIndividu {
    
         Connection connect;
      Statement stm;

    public UserIndividuServices() {
        connect=MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouter(Individu I , Utilisateur U) throws SQLException {
            PreparedStatement preUser = connect.prepareStatement("INSERT INTO utilisateur (email,password,numTel,type)VALUES (?,?,?,?);");
        preUser.setString(1, U.getEmail());
        preUser.setString(2, doHashing(U.getPassword()));
        preUser.setInt(3, U.getNumTel());
        preUser.setString(4, U.getType());
        preUser.executeUpdate();

        PreparedStatement stm = connect.prepareStatement("select idUtilisateur from utilisateur where email =?;");
        stm.setString(1, U.getEmail());
        ResultSet rst = stm.executeQuery();
        
        Utilisateur u = new Utilisateur ();
        while (rst.next()){
        u.setIdUtilisateur(rst.getInt("idUtilisateur"));
        }
      
        PreparedStatement pre = connect.prepareStatement("INSERT INTO individu (idUtilisateur,prenom)VALUES (?,?);");
        pre.setInt(1,u.getIdUtilisateur());      
        pre.setString(2, I.getPrenom());
       
                                    
        pre.executeUpdate();
        
    }

    @Override
    public boolean Update(Individu i) {
        System.out.println("a");
                    try {

            PreparedStatement pre = connect.prepareStatement("UPDATE individu i join utilisateur u on i.idUtilisateur=u.idUtilisateur SET nom = ? ,"
                    + " email=? , prenom= ?,numtel=?,"
                    + "sexe= ? ,    "
                    + "facebook= ?,instagram= ? ,"
                    + " whatsapp= ? where idIndividu= ? ;");
           pre.setString(1, i.getNom());
            pre.setString(2, i.getUtilisateur().getEmail());
               
           pre.setString(3, i.getPrenom());
           pre.setInt(4, i.getUtilisateur().getNumTel());
           //pre.setString(5, i.getDateNaissance());
           pre.setString(5, i.getSexe());
           pre.setString(6, i.getFacebook());
           pre.setString(7, i.getInstagram());
           pre.setString(8, i.getWhatsapp());
           pre.setInt(9,Statics.currentIndividu.getIdIndividu());

            

            

            if (pre.executeUpdate() != 0) {
                System.out.println("individu Updated successfully!!");
                 } else {
                System.out.println("not Updated!!!");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }


    @Override
    public boolean delete(int idIndividu) throws SQLException {
        PreparedStatement pre = connect.prepareStatement("Delete from individu where idIndividu=? ;");
        pre.setInt(1, idIndividu);
        if (pre.executeUpdate() != 0) {
            System.out.println("individu Deleted");
            return true;
        }
        System.out.println("id of individu not found");
        return false;

    }
 
    
 
   public String doHashing (String password) {
         try {
          MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

          messageDigest.update(password.getBytes());

          byte[] resultByteArray = messageDigest.digest();

          StringBuilder sb = new StringBuilder();

          for (byte b : resultByteArray) {
           sb.append(String.format("%02x", b));
          }

          return sb.toString();

         } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
         }

         return "";
        }
   public Boolean verifLogin (String email ,String  mdp)
     {
         String hashedMdp= doHashing(mdp); 
         String requete = "select * from utilisateur where email=? and password=?";
                 try {
            PreparedStatement ps = connect.prepareStatement(requete);
                     ps.setString(1, email);
                     ps.setString(2, hashedMdp);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
             
                return true ;
 
            }
            else{
                           

            return false;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return false;
        }}
    
    @Override
    public List<Individu> afficherIndividu() throws SQLException {
               
        List<Individu> Individu = new ArrayList<>();
        String req = "select * from individu i join utilisateur u on i.idUtilisateur=u.idUtilisateur";
        stm = connect.createStatement();
        ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
            Individu k = new Individu(rst.getInt("idIndividu"),
                       new Utilisateur(rst.getInt     ("idUtilisateur"),rst.getString("email"),rst.getInt("numTel"))  ,
            rst.getString("nom"),
            rst.getString("prenom"),
            rst.getString("sexe"),
            rst.getString("adresse"),
                        rst.getString("facebook"),
                        rst.getString("instagram"),
                        rst.getString("whatsapp"));



            Individu.add(k);
        }
        return Individu;
    }
    
    public Individu findIndividuByIdUtilisateur(Utilisateur u) throws SQLException{
         Individu i = new Individu();
          try {
       PreparedStatement req = connect.prepareStatement("select * from individu where idUtilisateur=?");
            req.setInt(1,u.getIdUtilisateur());
   
             ResultSet rst= req.executeQuery();
             while(rst.next())
             {
               
                 //String nom, String prenom, String sexe, String date,String email, String login, String mdp, String role
                 i =   new Individu(rst.getInt("idIndividu"),u,rst.getString("nom"),rst.getString("prenom"),rst.getString("sexe"),rst.getDate("dateNaissance"),rst.getString("adresse"),
                        rst.getString("facebook"),
                        rst.getString("instagram"),
                        rst.getString("whatsapp"),
                        rst.getBoolean("proprietaireChien"));
             }
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
return i;
    }
    
     public Individu findIndividuById(int id) throws SQLException{
         Individu i = new Individu();
          try {
       PreparedStatement req = connect.prepareStatement("select * from individu where idIndividu=?");
            req.setInt(1,id);
   
             ResultSet rst= req.executeQuery();
             while(rst.next())
             {
               
                 //String nom, String prenom, String sexe, String date,String email, String login, String mdp, String role
                 i =   new Individu(rst.getInt("idIndividu"),Statics.currentIndividu.getUtilisateur(),rst.getString("nom"),rst.getString("prenom"),rst.getString("sexe"),rst.getDate("dateNaissance"),rst.getString("adresse"),
                        rst.getString("facebook"),
                        rst.getString("instagram"),
                        rst.getString("whatsapp"),
                        rst.getBoolean("proprietaireChien"));
             }
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
return i;
    }
    @Override
    public int getDogsNumberByIdIndividu(int id) throws SQLException {
         int i=0;
       PreparedStatement req = connect.prepareStatement("select count(*) from chien where idIndividu=?");
            req.setInt(1,id);
   
             ResultSet rst= req.executeQuery();
            while(rst.next())
             {
              i= rst.getInt(1);
             }
            return i;
 
    }
    
    public List<Individu> findIndividuByName(String u) throws SQLException{
       List<Individu> L = new ArrayList();
          try {
       PreparedStatement req = connect.prepareStatement("select * from individu where nom like ?");
            req.setString(1,"%"+u+"%");

             ResultSet rst= req.executeQuery();
             while(rst.next())
             {
                 Utilisateur nu = new Utilisateur(rst.getInt("idUtilisateur"));
                 Individu i = new Individu(rst.getInt("idIndividu"),nu,rst.getString("nom"),rst.getString("prenom"),
                 rst.getString("sexe"),rst.getString("adresse"),rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
                    L.add(i);
             }
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
return L;
    }
}
