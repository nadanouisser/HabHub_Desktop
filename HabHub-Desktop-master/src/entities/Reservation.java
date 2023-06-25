/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.logging.Logger;
import java.sql.Timestamp;
import java.util.Date;
/**
 *
 * @author User
 */
public class Reservation {
    int idReservation;
    Individu individu;
    ServiceBusiness serviceBusiness;
    Date dateReservation;
    String heureReservation;

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getHeureReservation() {
        return heureReservation;
    }

    public void setHeureReservation(String heureReservation) {
        this.heureReservation = heureReservation;
    }

    public Individu getIndividu() {
        return individu;
    }

    public void setIndividu(Individu individu) {
        this.individu = individu;
    }

    public ServiceBusiness getServiceBusiness() {
        return serviceBusiness;
    }

    public void setServiceBusiness(ServiceBusiness serviceBusiness) {
        this.serviceBusiness = serviceBusiness;
    }

    

    public Reservation( Individu individu,ServiceBusiness serviceBusiness,Date dateReservation,String heureReservation) {
        this. individu = individu;
        this.serviceBusiness = serviceBusiness;
        this.dateReservation = dateReservation;
        this.heureReservation = heureReservation;

    }
    public Reservation(int idReservation,Individu individu,ServiceBusiness serviceBusiness, Date dateReservation,String heureReservation) {
        this.idReservation = idReservation;
        this.individu = individu;
        this.serviceBusiness = serviceBusiness;
        this.dateReservation = dateReservation;
        this.heureReservation = heureReservation;

    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

   

    public void setIdBusinessServices(ServiceBusiness serviceBusiness) {
        this.serviceBusiness = serviceBusiness;
    }

   
    public int getIdReservation() {
        return idReservation;
    }

    

    public ServiceBusiness getBusinessServices() {
        return serviceBusiness;
    }

   
    
    
      @Override
    public String toString() {
        return "Reservation{" + "idReservation=" + idReservation + ", idIndividu=" + individu.getIdIndividu() + ", idBusinessServices=" + serviceBusiness.idBusinessServices + ", dateReservation=" + dateReservation + ", dateHeureFin=" + heureReservation + '}';
    }
}
