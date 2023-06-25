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
public class Magasin {
    private int idMagasin,codePostal,RIB;
    private String nomMag,nomGest,adresse,ville,nomRL,matricule,idFiscal,cinRL;
    private byte patente;
    private Utilisateur user;

    public Magasin(int idMagasin, Utilisateur user, int codePostal, String cinRL, int RIB, String nomMag, String nomGest, String adresse, String ville, String nomRL, String matricule, String idFiscal, byte patente) {
        this.idMagasin = idMagasin;
        this.user = user;
        this.codePostal = codePostal;
        this.cinRL = cinRL;
        this.RIB = RIB;
        this.nomMag = nomMag;
        this.nomGest = nomGest;
        this.adresse = adresse;
        this.ville = ville;
        this.nomRL = nomRL;
        this.matricule = matricule;
        this.idFiscal = idFiscal;
        this.patente = patente;
    }

    public Magasin(Utilisateur user, int codePostal, String cinRL, int RIB, String nomMag, String nomGest, String adresse, String ville, String nomRL, String matricule, String idFiscal ) {
        this.user = user;
        this.codePostal = codePostal;
        this.cinRL = cinRL;
        this.RIB = RIB;
        this.nomMag = nomMag;
        this.nomGest = nomGest;
        this.adresse = adresse;
        this.ville = ville;
        this.nomRL = nomRL;
        this.matricule = matricule;
        this.idFiscal = idFiscal;
        
        
    }

 
    
    

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getCinRL() {
        return cinRL;
    }

    public void setCinRL(String cinRL) {
        this.cinRL = cinRL;
    }

    public int getRib() {
        return RIB;
    }

    public void setRib(int RIB) {
        this.RIB = RIB;
    }

    public String getNomMag() {
        return nomMag;
    }

    public void setNomMag(String nomMag) {
        this.nomMag = nomMag;
    }

    public String getNomGest() {
        return nomGest;
    }

    public void setNomGest(String nomGest) {
        this.nomGest = nomGest;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNomRL() {
        return nomRL;
    }

    public void setNomRL(String nomRL) {
        this.nomRL = nomRL;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getIdFiscal() {
        return idFiscal;
    }

    public void setIdFiscal(String idFiscal) {
        this.idFiscal = idFiscal;
    }

    public byte getPatente() {
        return patente;
    }

    public void setPatente(byte patente) {
        this.patente = patente;
    }

    @Override
    public String toString() {
        return "Magasin{" + "idMagasin=" + idMagasin + ", codePostal=" + codePostal + ", RIB=" + RIB + ", nomMag=" + nomMag + ", nomGest=" + nomGest + ", adresse=" + adresse + ", ville=" + ville + ", nomRL=" + nomRL + ", matricule=" + matricule + ", idFiscal=" + idFiscal + ", cinRL=" + cinRL + ", patente=" + patente + ", user=" + user + '}';
    }

  
    
    
}
