/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;





/**
 *
 * @author asus
 */
public class Commande {
      private int idCommande;
    private int idUtilisateur;
    private Date dateCommande;

    public Commande() {
    }

    public Commande(int idCommande, int idUtilisateur,Date dateCommande) {
        this.idCommande = idCommande;
        this.idUtilisateur = idUtilisateur;
        this.dateCommande=dateCommande;
                }

    public Commande(int idUtilisateur, Date dateCommande) {
        this.idUtilisateur = idUtilisateur;
        this.dateCommande = dateCommande;
    }
    
    

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommande=" + idCommande + ", idUtilisateur=" + idUtilisateur + ", dateCommande=" + dateCommande + '}';
    }
    
}
