/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package habhub;

import entities.AnnonceProprietaireChien;

import entities.Utilisateur;
import entities.Individu;
import entities.Chien;
import services.AnnonceProprietaireChienService;

import services.ChienService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.List;




public class HabHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Individu i = new Individu(1);

        Chien c = new Chien(11,i,"aa","M","2mois",true,"aaaaa","14.png","white","Husky","Hunting");
        ChienService cs = new ChienService();
        try{
          System.out.println(cs.findChienByIndividu(1));
       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     /*   AnnonceProprietaireChien acp = new AnnonceProprietaireChien(11,c,new Date("2009/10/10"),"hentita","P",new Date("2009/10/10"),"Rades","heeeeeeelp");
                AnnonceProprietaireChien ac = new AnnonceProprietaireChien(12,c,new Date("2009/10/10"),"hentita","A","lBorj");

        AnnonceProprietaireChienService sa=new AnnonceProprietaireChienService();
List<AnnonceProprietaireChien> annonces=new ArrayList<>();        
         try {
            annonces=sa.afficherAnnonceProprietaireChien("P");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         System.out.println(annonces);
          */
         /* try {
            sa.ajouterAnnonceProprietaireChien(ac);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
            try {
            System.out.println(sa.FilterAnnonceProprietaireChien("P","localisation","Ezzahra"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
        try {
            cs.ajouterChienProprietaire(c);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            sa.ajouterAnnonceAccouplement(a);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        pcs.ajouterProprietaireChien(pc);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         try {
            sa.ajouterAnnonceChienPerdu(a);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        */
        
        
            //pcs.updateProprietaireChien(3, "test modification");
            //cs.updateChien(1,"test","M","5mois",true,"Golden");
          /*  try {
           pcs.delete(5);
            
            
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
       
      /*try {
            
          */
          
            /*
        
       
        
        */
        /*
        try {
            System.out.println(pcs.afficherProprietaireChien());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        */
        /* try {
            //System.out.println(cs.afficherChiens());
            System.out.println(c.getIdChien());
            System.out.println(cs.findChienById(c.getIdChien()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        /* try {
            System.out.println(sa.afficherAnnonceAccouplement());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } */
         /*
          
         
         */
    
    }
        
        
          
    }
    

