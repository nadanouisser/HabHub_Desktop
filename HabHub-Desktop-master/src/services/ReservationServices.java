/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.*;
import entities.Business;
import entities.Individu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import utils.MyDB;
import entities.Reservation;
import entities.ServiceBusiness;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ReservationServices implements Interface_Reservation {
      Connection connect;
      Statement stm;

    public ReservationServices() {
      connect=MyDB.getInstance().getConnexion();
  
    }
   public void ajouter(Reservation R)throws SQLException {
        PreparedStatement pre = connect.prepareStatement("INSERT INTO reservation (idIndividu,idBusinessServices,dateReservation,heureReservation)VALUES (?,?,?,?);");
        pre.setInt(1, R.getIndividu().getIdIndividu());
        pre.setInt(2, R.getBusinessServices().getIdBusinessServices());
        java.sql.Date dateReservation = new java.sql.Date(R.getDateReservation().getTime());
        pre.setDate(3,dateReservation);
        pre.setString(4, R.getHeureReservation());

        
        

        pre.executeUpdate();
   }
   
     
    public boolean Update(Reservation r) {
            try {

            PreparedStatement pre = connect.prepareStatement("UPDATE reservation SET idIndividu = ? , idBusinessServices= ? , dateReservation= ? , heureReservation= ? where idReservation= ? ;");
            pre.setInt(1,r.getIndividu().getIdIndividu());
            pre.setInt(2, r.getBusinessServices().getIdBusinessServices());
            java.sql.Date dateReservation = new java.sql.Date(r.getDateReservation().getDate());
            pre.setDate(3,dateReservation);
            pre.setString(4, r.getHeureReservation());

            pre.setInt(5,r.getIdReservation());

            if (pre.executeUpdate() != 0) {
                System.out.println(" Reservation Updated");
                 } else {
                System.out.println("not Updated");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
         public boolean delete(Integer idReservation) throws SQLException {

        PreparedStatement pre = connect.prepareStatement("Delete from reservation where idReservation=? ;");
        pre.setInt(1, idReservation);
        if (pre.executeUpdate() != 0) {
            System.out.println("Review Deleted");
            return true;
        }
        System.out.println("id of Review not found");
        return false;

         }
         
        public List<Reservation> afficherReservation() throws SQLException{
        List<Reservation> reservations = new ArrayList<>();
        String req = "select * from reservation;";
        stm = connect.createStatement();
        ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
            Reservation R = new Reservation (rst.getInt("idReservation"),
            new Individu(rst.getInt("idIndividu")),
            new ServiceBusiness(rst.getInt("idBusinessServices")),
            rst.getDate("dateReservation"),rst.getString("heureReservation"));



            reservations.add(R);
        }
        return reservations;
        }
        
        public List<Reservation> afficherMesReservations() throws SQLException{
        List<Reservation> reservations = new ArrayList<>();
        String req = "select * from reservation;";
        stm = connect.createStatement();
        ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
            Reservation R = new Reservation (rst.getInt("idReservation"),
            new Individu(rst.getInt("idIndividu")),
            new ServiceBusiness(rst.getInt("idBusinessServices")),
            rst.getDate("dateReservation"),rst.getString("heureReservation"));


            reservations.add(R);
        }
        return reservations;
        }
        
}
 




