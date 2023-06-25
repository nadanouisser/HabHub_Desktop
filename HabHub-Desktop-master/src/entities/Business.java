/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import services.ServiceBusinessServices;

/**
 *
 * @author User
 */
public class Business {
    int idBusiness;
    Utilisateur user;
    String titre; 
    String description;
    String horaire;
    String ville;
    String localisation;
    String type;
    int experience;
    int optional2;
    String image;

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

  
    List<ServiceBusiness> BServices = new ArrayList<>();

    public List<ServiceBusiness> getBServices() {
        return BServices;
    }

    public void setBServices(List<ServiceBusiness> BServices) {
        this.BServices = BServices;
    }

        
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
   public Business(){
    } 
    
    public Business(int idBusiness){
        this.idBusiness=idBusiness;  
    }
    public Business(String titre){
        this.titre=titre;  
    }
     public Business(int idBusiness,String titre){
        this.idBusiness=idBusiness;  
        this.titre=titre;  
    }
        public Business(int idBusiness,Utilisateur user ,String titre){
        this.idBusiness=idBusiness; 
        this.user=user;  
        this.titre=titre;  
    }
    public Business(String titre,String description){
        this.titre=titre;  
        this.description=description;  

    }
    
    public Business( String titre, String description, String horaire, String ville, String localisation,String type) {
        this.titre = titre;
        this.description = description;
        this.horaire = horaire;
        this.ville = ville;
        this.localisation = localisation;
        this.type = type;

    }
    public Business(int idBusiness, String titre, String description, String horaire, String ville, String localisation,String type) {
        this.idBusiness = idBusiness;
        this.titre = titre;
        this.description = description;
        this.horaire = horaire;
        this.ville = ville;
        this.localisation = localisation;
        this.type = type;

    
    }

    public Business(int idBusiness, Utilisateur user, String titre, String description, String horaire, String ville, String localisation,String type,int experience,int optional2,String image) {
        this.idBusiness = idBusiness;
        this.user = user;
        this.titre = titre;
        this.description = description;
        this.horaire = horaire;
        this.ville = ville;
        this.localisation = localisation;
        this.type = type;
        this.experience=experience;
        this.optional2=optional2;
        this.image=image;



    }
    public Business(int idBusiness,String titre, String description, String horaire, String ville, String localisation,String type,List<ServiceBusiness> BServices ,int experience,int optional2,String image) {
        this.idBusiness = idBusiness;
        this.titre = titre;
        this.description = description;
        this.horaire = horaire;
        this.ville = ville;
        this.localisation = localisation;
        this.type = type;
        this.BServices=BServices;
        this.experience=experience;
        this.optional2=optional2;
        this.image=image;



    }
       public Business(int idBusiness,String titre, String description, String horaire, String ville, String localisation,String type,int experience,int optional2,String image) {
        this.idBusiness = idBusiness;
        this.titre = titre;
        this.description = description;
        this.horaire = horaire;
        this.ville = ville;
        this.localisation = localisation;
        this.type = type;
        //this.BServices=BServices;
        this.experience=experience;
        this.optional2=optional2;
        this.image=image;


    }
/*
    
    public Business(int idBusiness, Utilisateur user, String titre, String description, String horaire, String ville, String localisation,String type,) {
        this.idBusiness = idBusiness;
        this.user = user;
        this.titre = titre;
        this.description = description;
        this.horaire = horaire;
        this.ville = ville;
        this.localisation = localisation;
        this.type = type;

    }*/
    public int getIdBusiness() {
        return idBusiness;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
     
        return description;
    }


    public String getHoraire() {
        return horaire;
    }

    public String getVille() {
        return ville;
    }

    public String getLocalisation() {
        return localisation;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public void setIdBusiness(int idBusiness) {
        this.idBusiness = idBusiness;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
    @Override
    public String toString() {
        return "Business{" + "idBusiness=" + idBusiness +/* ", idUtilisateur=" + user.idUtilisateur +*/", titre=" + titre + ", image=" + image + ", description=" + description + ", horaire=" + horaire + ", ville=" + ville + ", localisation=" + localisation + ", type=" + type /*+", listBS=" + BServices */+", experience=" + experience+", image=" + image+'}';
    }
    
}
