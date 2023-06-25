/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Individu;
import entities.Utilisateur;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author NADA_USER
 */
public class UtilisateurService implements  IUtilisateur <Utilisateur >{
    


    Connection connect;
    Statement stm;

    public UtilisateurService() {
        connect = MyDB.getInstance().getConnexion();
    }
    

        @Override
    public boolean login(String email, String password) throws SQLException {
             List<Utilisateur> utilisateurs=new ArrayList<>();
      String req="SELECT * FROM UTILISATEUR where (emil='"+email+"' AND password='"+password+"')";
      
             //exec
             Statement st=connect.createStatement();
             ResultSet rs= st.executeQuery(req);
             while(rs.next())
                {
               //  int idUtilisateur ; String email ;  String password; int numTel;  String type 
                 utilisateurs.add(new Utilisateur(rs.getInt("idUtilisateur"),rs.getString("email"), rs.getString("password"), rs.getInt("numtel"), rs.getString("type")));
                 
                }
              if(utilisateurs.isEmpty()){
                  return false;
                } 
              else{
                  return true;
                }
         
         
                 
    }

    


        @Override

    public List<Utilisateur> TrierUtilisateur() throws SQLException{
            List<Utilisateur> utilisateurs=new ArrayList<>();
      String req="SELECT * FROM UTILISATEUR ORDER BY nom ";
      
             //exec
             Statement st=connect.createStatement();
             ResultSet rs= st.executeQuery(req);
             while(rs.next())
             {
                 //String nom, String prenom, String sexe, String date,String email, String login, String mdp, String role
                 utilisateurs.add(new Utilisateur(rs.getInt("idUtilisateur"),rs.getString("email"), rs.getString("password"), rs.getInt("numtel"), rs.getString("type")));
             
         } 
return utilisateurs;
    }

    @Override
    public Utilisateur chercherUtilisateur(String input)  {

        Utilisateur u = new Utilisateur();
          try {
       PreparedStatement req = connect.prepareStatement("select * from utilisateur where email=?");
        req.setString(1, input);
   
             ResultSet rs= req.executeQuery();
             while(rs.next())
             {
               
                 //String nom, String prenom, String sexe, String date,String email, String login, String mdp, String role
                 u =   new Utilisateur(rs.getInt("idUtilisateur"),rs.getString("email"), rs.getString("password"), rs.getInt("numtel"), rs.getString("type"));
             }
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
return u;    }

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

}
    

    

   
  
    
  
  



  

