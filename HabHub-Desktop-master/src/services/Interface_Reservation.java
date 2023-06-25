/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Reservation;
import entities.ServiceBusiness;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author User
 */
public interface Interface_Reservation {

    public void ajouter(Reservation R) throws SQLException;

    public boolean Update(Reservation R);

    public boolean delete(Integer idReservation) throws SQLException;

    public List<Reservation> afficherReservation() throws SQLException;

}
