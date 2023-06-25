/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.Date;
import entities.Chien;


public class AnnonceProprietaireChien {
    int idAnnonceProprietaireChien;
    Chien chien;
    Date datePublication;
    String description;
    String type;
    Date datePerte;
    String localisation;
    String messageVocal;

    public AnnonceProprietaireChien(Chien chien, String type, String localisation) {
        this.chien = chien;
        this.type = type;
        this.localisation = localisation;
    }


  public AnnonceProprietaireChien(int idAnnonceProprietaireChien, Chien chien, Date datePublication,String description,String type,String localisation ) {
        this.idAnnonceProprietaireChien=idAnnonceProprietaireChien;
        this.chien=chien;
        this.datePublication=datePublication;
        this.description=description;
        this.type=type;
        this.localisation=localisation;
             
    }

  public AnnonceProprietaireChien(int idAnnonceProprietaireChien, Chien chien, Date datePublication,String description,String type,Date datePerte,
          String localisation,String messageVocal ) {
        this.idAnnonceProprietaireChien=idAnnonceProprietaireChien;
        this.chien=chien;
        this.datePublication=datePublication;
        this.description=description;
        this.type=type;
        this.datePerte=datePerte;
        this.localisation=localisation;
        this.messageVocal=messageVocal;
             
    }

    

    public int getIdAnnonceProprietaireChien() {
        return idAnnonceProprietaireChien;
    }

    public void setIdAnnonceProprietaireChien(int idAnnonceProprietaireChien) {
        this.idAnnonceProprietaireChien = idAnnonceProprietaireChien;
    }

    public Chien getChien() {
        return chien;
    }

    public void setChien(Chien chien) {
        this.chien = chien;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDatePerte() {
        return datePerte;
    }

    public void setDatePerte(Date datePerte) {
        this.datePerte = datePerte;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getMessageVocal() {
        return messageVocal;
    }

    public void setMessageVocal(String messageVocal) {
        this.messageVocal = messageVocal;
    }


    
  @Override
    public String toString() {
        return "AnnonceProprietaireChien{" + "idAnnonceProprietaireChien=" + idAnnonceProprietaireChien + ", \r\n chien=" + chien + ", datePublication=" + datePublication + ", description=" + description + ", type=" + type + ", datePerte=" + datePerte + ", localisation=" + localisation + ", messageVocal=" + messageVocal + '}';
    }


   
}
