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
 * @author asus
 */
public interface Icategorie<C> {
    public void ajouterc(C c) throws SQLException;
    public List<C> affichercategorie() throws SQLException;
    public void deleteCategorie(int idCatgeorie) throws SQLException;
   public boolean updateCategorie(int idCategorie,String nom,String description) ;
}