/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IProduit<P> {
    public void ajouterp(P p) throws SQLException;
    public List<P> afficherproduit() throws SQLException;
     public void deleteProduit(int idProduit) throws SQLException;
     public boolean updateProduit(int idProduit,  Produit p) ;
     public List<P>afficherProduitTriP()throws SQLException;
     public List<P>afficherProduitParCategorie(String categorie)throws SQLException;
      public List<P>RechercheProduit(String nom)throws SQLException;
      public List<P>addichierProduitParNote()throws SQLException;
    
    
}
