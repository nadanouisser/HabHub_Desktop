/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

public class Chien {

    int idChien;
    Individu individu;
    String nom;
    String sexe;
    String age;
    Boolean vaccination;
    String description;
    String image;
     String color;
    String race;
    String groupe;
    int like;
    boolean playWithMe;

    public Chien(String nom, String sexe, String age, Boolean vaccination, String description, String color, String race, String groupe) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.vaccination = vaccination;
        this.description = description;
        this.color = color;
        this.race = race;
        this.groupe = groupe;
    }



    public Chien(String nom, String age,String description, String color, String race, String groupe) {
        this.nom = nom;
        this.age = age;
        this.description = description;
        this.color = color;
        this.race = race;
        this.groupe = groupe;
    }

    

    public Chien(int idChien, Individu individu, String nom, String sexe, String age, Boolean vaccination, String description, String image, String color, String race, String groupe, boolean playWithMe) {
        this.idChien = idChien;
        this.individu = individu;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.vaccination = vaccination;
        this.description = description;
        this.image = image;
        this.color = color;
        this.race = race;
        this.groupe = groupe;
        this.playWithMe = playWithMe;
    }
                                                                        
    public Chien(int idChien, Individu individu, String nom, String sexe, String age, Boolean vaccination, String description, String image, String color, String race, String groupe, int like) {
        this.idChien = idChien;
        this.individu = individu;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.vaccination = vaccination;
        this.description = description;
        this.image = image;
        this.color = color;
        this.race = race;
        this.groupe = groupe;
        this.like = like;
    }

   
   
    public Chien() {
    }

    public Chien(Individu individu, String nom, String sexe, String age, Boolean vaccination, String description, String image, String color, String race, String groupe) {
        this.individu = individu;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.vaccination = vaccination;
        this.description = description;
        this.image = image;
        this.color = color;
        this.race = race;
        this.groupe = groupe;
    }

    public Chien(int idChien, String nom, String sexe, String age, Boolean vaccination, String description, String image, String color, String race, String groupe) {
        this.idChien = idChien;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.vaccination = vaccination;
        this.description = description;
        this.image = image;
        this.color = color;
        this.race = race;
        this.groupe = groupe;
    }

    public Chien(int idChien) {
        this.idChien = idChien;
    }

    public Chien(String nom, String sexe, String age, Boolean vaccination, String description, String image, String color, String race, String groupe) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.vaccination = vaccination;
        this.description = description;
        this.image = image;
        this.color = color;
        this.race = race;
        this.groupe = groupe;
    }

    public Chien(int idChien, Individu individu, String nom, String sexe, String age, Boolean vaccination, String description, String image, String color, String race, String groupe) {
        this.idChien = idChien;
        this.individu = individu;
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.vaccination = vaccination;
        this.description = description;
        this.image = image;
        this.color = color;
        this.race = race;
        this.groupe = groupe;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String group) {
        this.groupe = group;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdChien() {
        return idChien;
    }

    public void setIdChien(int idChien) {
        this.idChien = idChien;
    }

    public Individu getIndividu() {
        return individu;
    }

    public void setIndividu(Individu individu) {
        this.individu = individu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Boolean getVaccination() {
        return vaccination;
    }

    public void setVaccination(Boolean vaccination) {
        this.vaccination = vaccination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
     public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public boolean isPlayWithMe() {
        return playWithMe;
    }

    public void setPlayWithMe(boolean playWithMe) {
        this.playWithMe = playWithMe;
    }

    @Override
    public String toString() {
        return "Chien{" + "idChien=" + idChien + ", individu=" + individu + ", nom=" + nom + ", sexe=" + sexe + ", age=" + age + ", vaccination=" + vaccination + ", description=" + description + ", image=" + image + ", color=" + color + ", race=" + race + ", groupe=" + groupe + ", like=" + like + ", playWithMe=" + playWithMe + '}';
    }

    
    

  

}
