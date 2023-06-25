/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Business;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface Interface_Business {

    public void ajouter(Business B) throws SQLException;

    public boolean Update(int idBusiness, String titre, String description, float prix, String horaire, String ville, String localisation);

    public boolean delete(Integer idBusiness) throws SQLException;

    public List<Business> afficherBusiness() throws SQLException;

    public List<Business> afficherVet() throws SQLException; // jointure business and business services

    public List<Business> TriVetByPriceAsc() throws SQLException;

    public List<Business> TriVetByPriceDesc() throws SQLException;

}
