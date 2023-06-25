/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Business;
import entities.ServiceBusiness;
import entities.Utilisateur;
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
public class ServiceBusinessServices implements Interface_ServiceBusiness {
      Connection connect;
      Statement stm;

    public ServiceBusinessServices() {
      connect=MyDB.getInstance().getConnexion();
        
}
      @Override
    public void ajouter(ServiceBusiness S)throws SQLException {
        Business B = new Business(9);
        PreparedStatement pre = connect.prepareStatement("INSERT INTO business_services (idBusiness,nomService,prix)VALUES (?,?,?);");
        pre.setInt(1, B.getIdBusiness());
        pre.setString(2, S.getNomService());
        pre.setFloat(3,S.getPrix());

        pre.executeUpdate();
   }
 

 public boolean Update(String nomService,float prix,int idBusinessServices) {
            try {

            PreparedStatement pre = connect.prepareStatement("UPDATE business_services  nomService= ? , prix= ?  where idBusinessServices= ? ;");
            pre.setString(1, nomService);
            pre.setFloat(2, prix);   
            pre.setInt(3,idBusinessServices);

            if (pre.executeUpdate() != 0) {
                System.out.println(" Business Service Updated");
                 } else {
                System.out.println("not Updated");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

 public boolean delete(Integer idBusinessServices) throws SQLException {

        PreparedStatement pre = connect.prepareStatement("Delete from business_services where idBusinessServices=? ;");
        pre.setInt(1, idBusinessServices);
        if (pre.executeUpdate() != 0) {
            System.out.println("Service Deleted");
            return true;
        }
        System.out.println("id of Service not found");
        return false;

    }
 
    public List<ServiceBusiness> afficherBusinessServices() throws SQLException{
        List<ServiceBusiness> BServices = new ArrayList<>();
        String req = "select * from business_services;";
        stm = connect.createStatement();
        ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
            ServiceBusiness BS = new ServiceBusiness (rst.getInt("idBusinessServices"),
            new Business(rst.getInt("idBusiness")),
            rst.getString("nomService"),
            rst.getFloat("prix"));
//SELECT * from business_services BS JOIN business B on BS.idBusiness=B.idBusiness join utilisateur U on U.idUtilisateur=B.idUtilisateur where U.type='business'
            BServices.add(BS);
        }
        return BServices;
        }
    
    public List<ServiceBusiness> afficherServicesById(int businessId) throws SQLException{
        List<ServiceBusiness> BServices = new ArrayList<>();
        PreparedStatement stm = connect.prepareStatement("select * from business_services where idBusiness=?;");
        stm.setInt(1,businessId);
        ResultSet rst = stm.executeQuery();
         
            while (rst.next()) {
            ServiceBusiness BS = new ServiceBusiness (rst.getInt("idBusinessServices"),
            new Business(rst.getInt("idBusiness")),
            rst.getString("nomService"),
            rst.getFloat("prix"));
            BServices.add(BS);
        }
        return BServices;
        }
    public List<ServiceBusiness> afficherServicesBack() throws SQLException{
        List<ServiceBusiness> BServices = new ArrayList<>();
        PreparedStatement stm = connect.prepareStatement("select * from business_services BS JOIN business B on BS.idBusiness=B.idBusiness JOIN utilisateur u on u.idUtilisateur=B.idUtilisateur");
        ResultSet rst = stm.executeQuery();
         
            while (rst.next()) {
            ServiceBusiness BS = new ServiceBusiness (rst.getInt("idBusinessServices"),
            new Business(rst.getInt("idBusiness"),new Utilisateur(rst.getString("email")),rst.getString("titre")),
            rst.getString("nomService"),
            rst.getFloat("prix"));
            BServices.add(BS);
        }
        return BServices;
        }
    

    
        public List<ServiceBusiness> filterBusinessById (int businessId) throws SQLException{
        List<ServiceBusiness> BServices = new ArrayList<>();
        PreparedStatement stm = connect.prepareStatement("select idBusinessServices,nomService,prix from business_services where idBusiness=?;");
        stm.setInt(1,businessId);
        ResultSet rst = stm.executeQuery();
         
            while (rst.next()) {
            ServiceBusiness BS = new ServiceBusiness (rst.getInt("idBusinessServices"),
            new Business(),
            rst.getString("nomService"),
            rst.getFloat("prix"));
            BServices.add(BS);
        }
        return BServices;
        
    }
    
    
      @Override
        public List<ServiceBusiness> filterBusinessByType (String businessType) throws SQLException{
        List<ServiceBusiness> BServices = new ArrayList<>();
        PreparedStatement stm = connect.prepareStatement("select idBusinessServices,titre,description,nomService,prix from business_services BS JOIN business B on BS.idBusiness=B.idBusiness where B.type=?;");
        stm.setString(1,businessType);
        ResultSet rst = stm.executeQuery();
         
            while (rst.next()) {
            ServiceBusiness BS = new ServiceBusiness (rst.getInt("idBusinessServices"),
            new Business(rst.getString("titre"),rst.getString("description")),
            rst.getString("nomService"),
            rst.getFloat("prix"));
            BServices.add(BS);
        }
        return BServices;
        
    }
        
            
     public List<ServiceBusiness> filterBusinessBy2Variables (String businessType,String filter,String value) throws SQLException{
        List<ServiceBusiness> BServices = new ArrayList<>();
        PreparedStatement stm = connect.prepareStatement("select * from business_services BS JOIN business B on BS.idBusiness=B.idBusiness where B.type=? and "+filter+"=?;");
        stm.setString(1,businessType);
        stm.setString(2,value);

        ResultSet rst = stm.executeQuery();
         
            while (rst.next()) {
            ServiceBusiness BS = new ServiceBusiness (rst.getInt("idBusinessServices"),
            new Business(rst.getString("titre")),
            rst.getString("nomService"),
            rst.getFloat("prix"));
            BServices.add(BS);
        }
        return BServices;
        
    }
             String req = "select * from business b JOIN utilisateur u on b.idUtilisateur=.u.idUtilisateur where b.type= ? and ( b.titre like ? or b.ville like ? or b.description like ?);";

      public List<ServiceBusiness> rechercherServicesBack(String input) throws SQLException{
        List<ServiceBusiness> BServices = new ArrayList<>();

        String req = "select * from business_services sb JOIN business b on sb.idBusiness=b.idBusiness JOIN utilisateur u on b.idUtilisateur=.u.idUtilisateur where ( sb.nomService like ? or u.email like ? or sb.prix like ?);";
        PreparedStatement ps = connect.prepareStatement(req);
        ps.setString(1, "%" + input+ "%");
        ps.setString(2, "%" + input+ "%");
        ps.setString(3, "%" + input+ "%");

        ResultSet rst = ps.executeQuery();
            
        while (rst.next()) {
            ServiceBusiness BS = new ServiceBusiness (rst.getInt("idBusinessServices"),
            new Business(rst.getInt("idBusiness"),new Utilisateur(rst.getString("email")),rst.getString("titre")),
            rst.getString("nomService"),
            rst.getFloat("prix"));
            BServices.add(BS);
        }
        return BServices;
      }
    
}