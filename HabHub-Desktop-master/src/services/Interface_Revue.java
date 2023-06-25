/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Business;
import entities.Individu;
import entities.Revue;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface Interface_Revue {
        public void ajouterRevueBusiness(Revue R)throws SQLException ;
        public boolean UpdateRevueBusiness(Individu indiv,int idProduit ,int nbEtoiles, String commentaire,int idRevue);
        public boolean deleteRevue(Integer idRevue) throws SQLException;
        public List<Revue> afficherAllRevues() throws SQLException ;
//        public List<Revue> afficherRevueBusiness(Business business) throws SQLException;
        public List<Revue> afficherRevueBusiness(String BusinessTitle) throws SQLException;

        public List<Revue> afficherRevueId(int BusinessId) throws SQLException ;



}
