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
public interface IMagasin<m> {
    public void ajouterMa(m ma) throws SQLException;
    public List<m> afficherMagasin() throws SQLException;
    public void deleteMagasin(int idMagasin) throws SQLException;
   public boolean updateMagasin(int idMagasin, int idUtilisateur, int codePostal, String cinRL, int RIB, String nomMag, String nomGest, String adresse, String ville, String nomRL, String matricule, String idFiscal) ;
    
}
