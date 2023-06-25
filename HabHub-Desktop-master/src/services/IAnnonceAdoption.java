/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Tun21
 */
public interface IAnnonceAdoption<I> {
    public void addAnnonceAdoption(I a) throws SQLException;
    public void deleteAnnonceAdoption(int id) throws SQLException;
    public List<I> displayAnnonceAdoption() throws SQLException;
    public List<I> rechercheAnnonceAdoption(String nom) throws SQLException;
}
