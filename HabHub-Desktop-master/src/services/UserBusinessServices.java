/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Business;
import entities.ServiceBusiness;
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

/**
 *
 * @author User
 */
public class UserBusinessServices {
     Connection connect;
      Statement stm;

    public UserBusinessServices() {
        connect=MyDB.getInstance().getConnexion();
    }
    
    public void ajouter(Business B,Utilisateur U)throws SQLException {
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
      
        PreparedStatement pre = connect.prepareStatement("INSERT INTO business (idUtilisateur,titre,description,horaire,ville,localisation,type,experience,image)VALUES (?,?,?,?,?,?,?,?,?);");
        pre.setInt(1,u.getIdUtilisateur());      
        pre.setString(2, B.getTitre());
        pre.setString(3, B.getDescription());
        pre.setString(4, B.getHoraire());
        pre.setString(5, B.getVille());
        pre.setString(6, B.getLocalisation());
        pre.setString(7, B.getType());
        pre.setInt(8, B.getExperience());
        pre.setString(9, B.getImage());

                                    
        pre.executeUpdate();

    }
    

        
     public boolean Update(int idBusiness,String titre,String description,float prix,String horaire,String ville,String localisation,String type,int experience,String image) {
            try {

            PreparedStatement pre = connect.prepareStatement("UPDATE business SET titre = ? , description= ? , horaire= ? , ville= ? , localisation= ? type=? image=? where idBusiness= ? ;");
            pre.setString(1, titre);
            pre.setString(2, description);   
            pre.setString(3,horaire);
            pre.setString(4, ville);
            pre.setString(5, localisation);
            pre.setString(6, type);
            pre.setInt(7, experience);
            pre.setString(9, image);
            pre.setInt(8, idBusiness);

            if (pre.executeUpdate() != 0) {
                System.out.println(" Business Updated");
                 } else {
                System.out.println("not Updated");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
      
 public boolean delete(Integer idBusiness) throws SQLException {

        PreparedStatement pre = connect.prepareStatement("Delete from business where idBusiness=? ;");
        pre.setInt(1, idBusiness);
        if (pre.executeUpdate() != 0) {
            System.out.println("business Deleted");
            return true;
        }
        System.out.println("id of business not found");
        return false;

    }
 
        public List<Business> afficherBusiness(String businessType) throws SQLException{
        List<Business> businesses = new ArrayList<>();
        ServiceBusinessServices SBS1 = new ServiceBusinessServices();

        String req = "select * from business where type= ? ;";
        PreparedStatement ps = connect.prepareStatement(req);
        ps.setString(1,businessType);      
       
        ResultSet rst = ps.executeQuery();
            
            while (rst.next()) {
            Business a = new Business(rst.getInt("idBusiness"),
            rst.getString("titre"),
            rst.getString("description"),
            rst.getString("horaire"),
            rst.getString("ville"),
            rst.getString("localisation"),
            rst.getString("type"),SBS1.filterBusinessById(rst.getInt("idBusiness")),rst.getInt("experience"),16,rst.getString("image") );


            businesses.add(a);
        }
        return businesses;
        }
        public List<Business> afficherBusinessJoinUser(String businessType) throws SQLException{
        List<Business> businesses = new ArrayList<>();
        ServiceBusinessServices SBS1 = new ServiceBusinessServices();

        String req = "select * from business b join utilisateur u on b.idUtilisateur=u.idUtilisateur where b.type= ? ;";
        PreparedStatement ps = connect.prepareStatement(req);
        ps.setString(1,businessType);      
       
        ResultSet rst = ps.executeQuery();
            
            while (rst.next()) {
            Business a = new Business(rst.getInt("idBusiness"),
            new Utilisateur(rst.getString("email")), 
            rst.getString("titre"),
            rst.getString("description"),
            rst.getString("horaire"),
            rst.getString("ville"),
            rst.getString("localisation"),rst.getString("type"),rst.getInt("experience"),16,rst.getString("image"));

            businesses.add(a);
        }
        return businesses;
        }
         public List<Business> rechercherBusinessByType(String businessType,String input) throws SQLException{
        List<Business> businesses = new ArrayList<>();
        ServiceBusinessServices SBS1 = new ServiceBusinessServices();

        String req = "select * from business where type= ? and ( titre like ? or ville like ? or description like ?);";
        PreparedStatement ps = connect.prepareStatement(req);
        ps.setString(1,businessType);      
        ps.setString(2, "%" + input+ "%");
        ps.setString(3, "%" + input+ "%");
        ps.setString(4, "%" + input+ "%");

        ResultSet rst = ps.executeQuery();
            
        while (rst.next()) {
        Business a = new Business(rst.getInt("idBusiness"),
        rst.getString("titre"),
        rst.getString("description"),
        rst.getString("horaire"),
        rst.getString("ville"),
        rst.getString("localisation"),
        rst.getString("type"),rst.getInt("experience"),16,rst.getString("image"));


            businesses.add(a);
        }
        return businesses;
        }
          public List<Business> rechercherBusinessBack(String businessType,String input) throws SQLException{
        List<Business> businesses = new ArrayList<>();

        String req = "select * from business b JOIN utilisateur u on b.idUtilisateur=.u.idUtilisateur where b.type= ? and ( b.titre like ? or b.ville like ? or b.description like ?);";
        PreparedStatement ps = connect.prepareStatement(req);
        ps.setString(1,businessType);      
        ps.setString(2, "%" + input+ "%");
        ps.setString(3, "%" + input+ "%");
        ps.setString(4, "%" + input+ "%");

        ResultSet rst = ps.executeQuery();
            
        while (rst.next()) {
            Business a = new Business(rst.getInt("idBusiness"),
            new Utilisateur(rst.getString("email")), 
            rst.getString("titre"),
            rst.getString("description"),
            rst.getString("horaire"),
            rst.getString("ville"),
            rst.getString("localisation"),rst.getString("type"),rst.getInt("experience"),16,rst.getString("image"));


            businesses.add(a);
        }
        return businesses;
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

         public void ajouterGrooming(Business B,Utilisateur U)throws SQLException {
        PreparedStatement preUser = connect.prepareStatement("INSERT INTO utilisateur (email,password,numTel,type)VALUES (?,?,?,?);");
        preUser.setString(1, U.getEmail());
        preUser.setString(2, doHashing(U.getPassword()));
        preUser.setInt(3, U.getNumTel());
        preUser.setString(4, "business");
        preUser.executeUpdate();
        
        PreparedStatement stm = connect.prepareStatement("select idUtilisateur from utilisateur where email =?;");
        stm.setString(1, U.getEmail());
        ResultSet rst = stm.executeQuery();
        
        Utilisateur u = new Utilisateur ();
        while (rst.next()){
        u.setIdUtilisateur(rst.getInt("idUtilisateur"));
        }
      
        PreparedStatement pre = connect.prepareStatement("INSERT INTO business (idUtilisateur,titre,description,horaire,ville,localisation,type,experience,image)VALUES (?,?,?,?,?,?,?,?,?);");
        pre.setInt(1,u.getIdUtilisateur());      
        pre.setString(2, B.getTitre());
        pre.setString(3, B.getDescription());
        pre.setString(4, B.getHoraire());
        pre.setString(5, B.getVille());
        pre.setString(6, B.getLocalisation());
        pre.setString(7, "grooming");
        pre.setInt(8, B.getExperience());
        pre.setString(9, B.getImage());

                                    
        pre.executeUpdate();

    }
          public void ajouterPark(Business B,Utilisateur U)throws SQLException {
        PreparedStatement preUser = connect.prepareStatement("INSERT INTO utilisateur (email,password,numTel,type)VALUES (?,?,?,?);");
        preUser.setString(1, U.getEmail());
        preUser.setString(2, doHashing(U.getPassword()));
        preUser.setInt(3, U.getNumTel());
        preUser.setString(4, "business");
        preUser.executeUpdate();
        
        PreparedStatement stm = connect.prepareStatement("select idUtilisateur from utilisateur where email =?;");
        stm.setString(1, U.getEmail());
        ResultSet rst = stm.executeQuery();
        
        Utilisateur u = new Utilisateur ();
        while (rst.next()){
        u.setIdUtilisateur(rst.getInt("idUtilisateur"));
        }
      
        PreparedStatement pre = connect.prepareStatement("INSERT INTO business (idUtilisateur,titre,description,horaire,ville,localisation,type,experience,image)VALUES (?,?,?,?,?,?,?,?,?);");
        pre.setInt(1,u.getIdUtilisateur());      
        pre.setString(2, B.getTitre());
        pre.setString(3, B.getDescription());
        pre.setString(4, B.getHoraire());
        pre.setString(5, B.getVille());
        pre.setString(6, B.getLocalisation());
        pre.setString(7, "park");
        pre.setInt(8, B.getExperience());
        pre.setString(9, B.getImage());

                                    
        pre.executeUpdate();

    }
           public void ajouterVet(Business B,Utilisateur U)throws SQLException {
        PreparedStatement preUser = connect.prepareStatement("INSERT INTO utilisateur (email,password,numTel,type)VALUES (?,?,?,?);");
        preUser.setString(1, U.getEmail());
        preUser.setString(2, doHashing(U.getPassword()));
        preUser.setInt(3, U.getNumTel());
        preUser.setString(4, "business");
        preUser.executeUpdate();
        
        PreparedStatement stm = connect.prepareStatement("select idUtilisateur from utilisateur where email =?;");
        stm.setString(1, U.getEmail());
        ResultSet rst = stm.executeQuery();
        
        Utilisateur u = new Utilisateur ();
        while (rst.next()){
        u.setIdUtilisateur(rst.getInt("idUtilisateur"));
        }
      
        PreparedStatement pre = connect.prepareStatement("INSERT INTO business (idUtilisateur,titre,description,horaire,ville,localisation,type,experience,image)VALUES (?,?,?,?,?,?,?,?,?);");
        pre.setInt(1,u.getIdUtilisateur());      
        pre.setString(2, B.getTitre());
        pre.setString(3, B.getDescription());
        pre.setString(4, B.getHoraire());
        pre.setString(5, B.getVille());
        pre.setString(6, B.getLocalisation());
        pre.setString(7, "vet");
        pre.setInt(8, B.getExperience());
        pre.setString(9, B.getImage());

                                    
        pre.executeUpdate();

    }
            public void ajouterDogSitting(Business B,Utilisateur U)throws SQLException {
        PreparedStatement preUser = connect.prepareStatement("INSERT INTO utilisateur (email,password,numTel,type)VALUES (?,?,?,?);");
        preUser.setString(1, U.getEmail());
        preUser.setString(2, doHashing(U.getPassword()));
        preUser.setInt(3, U.getNumTel());
        preUser.setString(4, "business");
        preUser.executeUpdate();
        
        PreparedStatement stm = connect.prepareStatement("select idUtilisateur from utilisateur where email =?;");
        stm.setString(1, U.getEmail());
        ResultSet rst = stm.executeQuery();
        
        Utilisateur u = new Utilisateur ();
        while (rst.next()){
        u.setIdUtilisateur(rst.getInt("idUtilisateur"));
        }
      
        PreparedStatement pre = connect.prepareStatement("INSERT INTO business (idUtilisateur,titre,description,horaire,ville,localisation,type,experience,image)VALUES (?,?,?,?,?,?,?,?,?);");
        pre.setInt(1,u.getIdUtilisateur());      
        pre.setString(2, B.getTitre());
        pre.setString(3, B.getDescription());
        pre.setString(4, B.getHoraire());
        pre.setString(5, B.getVille());
        pre.setString(6, B.getLocalisation());
        pre.setString(7, "dogsitting");
        pre.setInt(8, B.getExperience());
        pre.setString(9, B.getImage());

                                    
        pre.executeUpdate();

    }
    
 

   public boolean UpdateData(int idBusiness,Business b) {
            try {

            PreparedStatement pre = connect.prepareStatement("UPDATE business SET titre = ? , description= ? , horaire= ? , ville= ? , localisation= ? where idBusiness= ? ;");
            pre.setString(1, b.getTitre());
            pre.setString(2, b.getDescription());   
            pre.setString(3,b.getHoraire());
            pre.setString(4, b.getVille());
            pre.setString(5, b.getLocalisation());
           
            pre.setInt(6, idBusiness);

            if (pre.executeUpdate() != 0) {
                System.out.println(" Business Updated");
                 } else {
                System.out.println("not Updated");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }}