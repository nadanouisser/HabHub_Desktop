/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.ServiceBusiness;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface Interface_ServiceBusiness {
    public void ajouter(ServiceBusiness S)throws SQLException ;
     public boolean Update(String nomService,float prix,int idBusinessServices) ;
    public boolean delete(Integer idBusinessServices) throws SQLException ;
    public List<ServiceBusiness> afficherBusinessServices() throws SQLException;
    public List<ServiceBusiness> filterBusinessByType(String businessType) throws SQLException;
    public List<ServiceBusiness> filterBusinessBy2Variables (String businessType,String filter,String value) throws SQLException;
    public List<ServiceBusiness> afficherServicesById(int businessId) throws SQLException;
      public List<ServiceBusiness> rechercherServicesBack(String input) throws SQLException;


}
