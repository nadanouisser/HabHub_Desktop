/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
import entities.Individu;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author NADA_USER
 */
public interface IIndividu {
             public void ajouter(Individu I,Utilisateur U)throws SQLException;
        public boolean Update(Individu i) ;
        public boolean delete(int idIndividu) throws SQLException;
        public List<Individu> afficherIndividu() throws SQLException;
         public Individu findIndividuByIdUtilisateur(Utilisateur U) throws SQLException;
         public int getDogsNumberByIdIndividu(int id)throws SQLException;
}

