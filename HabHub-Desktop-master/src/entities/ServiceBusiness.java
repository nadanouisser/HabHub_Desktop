/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author User
 */
public class ServiceBusiness {
   int idBusinessServices;
   Business business;
   String nomService;

  
   float prix;
   
    public ServiceBusiness() {
       
    
    }
    public ServiceBusiness(int idBusinessServices) {
       
            this.idBusinessServices = idBusinessServices;

    }
    
    
      public ServiceBusiness(int idBusinessServices,String nomService, float prix) {
        this.idBusinessServices = idBusinessServices;
        this.business = business;
        this.nomService = nomService;
        this.prix = prix;
    }
    
   
    public ServiceBusiness(int idBusinessServices, Business business, String nomService, float prix) {
        this.idBusinessServices = idBusinessServices;
        this.business = business;
        this.nomService = nomService;
        this.prix = prix;
    }
    
    
     
    
  public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
    public int getIdBusinessServices() {
        return idBusinessServices;
    }

    public int getidBusiness() {
        return business.idBusiness;
    }

    public String getNomService() {
        return nomService;
    }

    public float getPrix() {
        return prix;
    }

    public void setIdBusinessServices(int idBusinessServices) {
        this.idBusinessServices = idBusinessServices;
    }

    public void setIdBusiness(Business business) {
        this.business.idBusiness = business.idBusiness;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "ServiceBusiness{" + "idBusinessServices=" + idBusinessServices + ", idBusiness=" + business.idBusiness + ", nomService=" + nomService + ", prix=" + prix + "} \r\n";
    }
    
   
}
