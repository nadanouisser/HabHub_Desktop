/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author asus
 */
public class Produit {
    private String image;

    public Produit(int idProduit, Categorie idCategorie, int nbetoiles, String nom, String description, String marque, float prix , String image) {
        this.image = image;
        this.idProduit = idProduit;
        this.idCategorie = idCategorie;
        this.nbetoiles = nbetoiles;
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.prix = prix;
    }
    private int idProduit;
    private Categorie idCategorie;
    private int nbetoiles;
    private String nom;
     private String description;
    private String marque;
    private float prix;

    public Produit(String nom, float prix , String image) {
        this.nom = nom;
        this.prix = prix;
        this.image=image;
    }
    
    
    
    public Produit( Categorie idCategorie ,int nbetoiles, String nom, String description, String marque, float prix) {
        this.nbetoiles = nbetoiles;
     
        this.idCategorie = idCategorie;
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
   

    public Produit(int idProduit, Categorie idCategorie, int nbetoiles, String nom, String description, String marque, float prix) {
        this.idProduit = idProduit;
        this.nbetoiles = nbetoiles;
     
        this.idCategorie = idCategorie;
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.prix = prix;
    }

    public Produit( String nom ,Categorie idCategorie, float prix, String description, String marque) {
        this.nom = nom;
        this.idCategorie = idCategorie;
        this.prix = prix;
        
        this.marque = marque;
        this.description = description;
    }

    public Produit(String nom , float prix, String description, String marque) {
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.prix = prix;
    }

  
   
    
    

 

    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Produit(int nbetoiles, String nom, String description, String marque, float prix) {
        this.nbetoiles = nbetoiles;
 
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.prix = prix;
    }
    
    
    

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbetoiles() {
        return nbetoiles;
    }

    public void setNbetoiles(int nbetoiles) {
        this.nbetoiles = nbetoiles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return "Produit{" + "image=" + image + ", idProduit=" + idProduit + ", idCategorie=" + idCategorie + ", nbetoiles=" + nbetoiles + ", nom=" + nom + ", description=" + description + ", marque=" + marque + ", prix=" + prix + '}';
    }

    public Produit( String nom, String marque, float prix , String image) {
        this.image = image;
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
    }



  


   
    
    
    
    
            
    
}
