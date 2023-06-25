package services;

import entities.AnnonceProprietaireChien;
import entities.Individu;
import entities.Utilisateur;
import entities.Chien;
import utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AnnonceProprietaireChienService {
     private Connection connect;
    private Statement ste;

    public AnnonceProprietaireChienService() {
        connect = MyDB.getInstance().getConnexion();

    }

    public void ajouterAnnonceProprietaireChien(AnnonceProprietaireChien a) throws SQLException {
        PreparedStatement pre = connect.prepareStatement("INSERT INTO annonce_proprietaire_chien (idChien, datePublication,description,type,datePerte,Localisation,messageVocal) VALUES (?,SYSDATE(),?,?,SYSDATE(),?,?);");
        pre.setInt(1, a.getChien().getIdChien());
      //  java.sql.Date sqlDatePublication = new java.sql.Date( a.getDatePublication().getTime() ); 
        //pre.setDate(2,sqlDatePublication);
        pre.setString(2, a.getDescription());
        pre.setString(3, a.getType());
        /*java.sql.Date sqlDatePerte=null;
        if (a.getDatePerte()!= null){
        sqlDatePerte = new java.sql.Date( a.getDatePerte().getTime() ); 
        }
        pre.setDate(4,sqlDatePerte);*/
        pre.setString(4, a.getLocalisation());
        pre.setString(5, a.getMessageVocal());
        pre.executeUpdate();
    }
   
     
    public boolean updateAnnonceProprietaireChien(AnnonceProprietaireChien apc) {
            try {
            PreparedStatement pre = connect.prepareStatement("UPDATE annonce_proprietaire_chien SET description= ? , datePerte= ? , localisation= ?,messageVocal=? where idAnnonceProprietaireChien=?;");
            pre.setString(1, apc.getDescription());
            java.sql.Date sqlDatePerte=null;
        if (apc.getDatePerte()!= null){
        sqlDatePerte = new java.sql.Date( apc.getDatePerte().getTime() ); 
        }
        pre.setDate(2,sqlDatePerte);
            pre.setString(3, apc.getLocalisation());
            pre.setString(4, apc.getMessageVocal());
            pre.setInt(5, apc.getIdAnnonceProprietaireChien());

            

            if (pre.executeUpdate() != 0) {
                System.out.println(" Annonce updated");
                 } else {
                System.out.println("Annonce not updated");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
  
    public boolean delete(int idAnnonceProprietaireChien) throws SQLException {

        PreparedStatement pre = connect.prepareStatement("Delete from annonce_proprietaire_chien where IdAnnonceProprietaireChien=? ;");
        pre.setInt(1, idAnnonceProprietaireChien);
        if (pre.executeUpdate() != 0) {
            System.out.println("Annonce Deleted");
            return true;
        }
        System.out.println("Annonce not found");
        return false;

    }
    
     public void deleteAnnonceProprietaireChienById(int id,String type) throws SQLException {

        PreparedStatement pre = connect.prepareStatement("Delete from annonce_proprietaire_chien where idChien=? and type=? ;");
        pre.setInt(1, id);
        pre.setString(2, type);
        if (pre.executeUpdate() != 0) {
            System.out.println("Annonce Deleted");
           
        }
        System.out.println("Annonce not found");
       

    }
   
        public List<AnnonceProprietaireChien> afficherAnnonceProprietaireChien(String type) throws SQLException {
        List<AnnonceProprietaireChien> annoncesProprietaireChien = new ArrayList<>();
        PreparedStatement ps = connect.prepareStatement("select * from annonce_proprietaire_chien a join chien c ON a.idChien=c.idChien join individu i on i.idIndividu=c.idIndividu join utilisateur u on i.idUtilisateur=u.idUtilisateur where a.type=? order by datePublication desc;");
        ps.setString(1,type);
            ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"),rst.getString("email"),rst.getInt("numTel"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            
            Chien nc = new Chien(rst.getInt("idChien"),ni,rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("c.description"),
                    rst.getString("c.image"),rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceProprietaireChien a = new AnnonceProprietaireChien(rst.getInt("idAnnonceProprietaireChien"),
            nc,
            rst.getDate("datePublication"),
            rst.getString("a.description"),
            rst.getString("type"),
            rst.getDate("datePerte"),
            rst.getString("localisation"),
            rst.getString("messageVocal"));
         
            
            annoncesProprietaireChien.add(a);
        }
        return annoncesProprietaireChien;
    }
        
        public List<AnnonceProprietaireChien> rechercheAnnonceProprietaireChien (String input,String type) throws SQLException {
        List<AnnonceProprietaireChien> annoncesProprietaireChien = new ArrayList<>();
        String req = "select * from annonce_proprietaire_chien a join chien c ON a.idChien=c.idChien join individu i on i.idIndividu=c.idIndividu where (a.localisation like ? or c.nom like ? or i.prenom like ? ) and (type=?) order by datePublication desc ";
        PreparedStatement ps = connect.prepareStatement(req);
            ps.setString(1, "%" + input+ "%");
            ps.setString(2, "%" + input+ "%");
            ps.setString(3, "%" + input+ "%");
            ps.setString(4, type);
            ResultSet rst = ps.executeQuery();
             while (rst.next()) {
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getInt("idChien"),ni,rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("c.image"),rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceProprietaireChien a = new AnnonceProprietaireChien(rst.getInt("idAnnonceProprietaireChien"),
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("type"),
            rst.getDate("datePerte"),
            rst.getString("localisation"),
            rst.getString("messageVocal"));
         
            
            annoncesProprietaireChien.add(a);
        }
        return annoncesProprietaireChien;
    }
     
    public List<AnnonceProprietaireChien> FilterAnnonceProprietaireChien(String type,String filtre,String valeur) throws SQLException {
        List<AnnonceProprietaireChien> annoncesProprietaireChien = new ArrayList<>();
        PreparedStatement ps = connect.prepareStatement("select * from annonce_proprietaire_chien a join chien c ON a.idChien=c.idChien join individu i on i.idIndividu=pc.idIndividu where type=? and "+filtre+" = ?;");
        ps.setString(1,type);
        ps.setString(2,valeur);
        ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getInt("idChien"),ni,rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("c.image"),rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceProprietaireChien a = new AnnonceProprietaireChien(rst.getInt("idAnnonceProprietaireChien"),
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("type"),
            rst.getDate("datePerte"),
            rst.getString("localisation"),
            rst.getString("messageVocal"));
         
            
            annoncesProprietaireChien.add(a);
        }
        return annoncesProprietaireChien;
    }
     public boolean checkDog(int id,String type) throws SQLException {

        PreparedStatement pre = connect.prepareStatement("select * from annonce_proprietaire_chien where idChien=? and type=? ;");
        pre.setInt(1, id);
        pre.setString(2, type);
        ResultSet rst = pre.executeQuery();
        if (rst.next()) {
             System.out.println("Annonce found");
            return true;
        }
        System.out.println("Annonce not found");
        return false;

    }
/*
    
      public void ajouterAnnonceAccouplement(AnnonceProprietaireChien a) throws SQLException {
        PreparedStatement pre = connect.prepareStatement("INSERT INTO annonce_proprietaire_chien (idChien, datePublication,description,type,Localisation)VALUES (?,?,?,?,?);");
        pre.setInt(1, a.getChien().getIdChien());
        java.sql.Date sqlDate = new java.sql.Date( a.getDatePublication().getTime() ); 
        pre.setDate(2,sqlDate);
        pre.setString(3, a.getDescription());
        pre.setString(4, a.getType());
        pre.setString(5, a.getLocalisation());
        pre.executeUpdate();
    }
     
     public void ajouterAnnonceChienPerdu(AnnonceProprietaireChien a) throws SQLException {
        PreparedStatement pre = connect.prepareStatement("INSERT INTO annonce_proprietaire_chien (idChien, datePublication,description,type,datePerte,Localisation,messageVocal) VALUES (?,?,?,?,?,?,?);");
        pre.setInt(1, a.getChien().getIdChien());
        java.sql.Date sqlDatePublication = new java.sql.Date( a.getDatePublication().getTime() ); 
        pre.setDate(2,sqlDatePublication);
        pre.setString(3, a.getDescription());
        pre.setString(4, a.getType());
        java.sql.Date sqlDatePerte = new java.sql.Date( a.getDatePerte().getTime() ); 
        pre.setDate(5,sqlDatePerte);
        pre.setString(6, a.getLocalisation());
        pre.setString(7, a.getMessageVocal());
        pre.executeUpdate();
    }
    
 public boolean updateAnnonceChienPerdu(int idAnnonceProprietaireChien,String description,String datePerte ,String localisation,String messageVocal) {
            try {
            PreparedStatement pre = connect.prepareStatement("UPDATE annonce_proprietaire_chien SET description= ? , datePerte= ? , localisation= ? ,messageVocal=? where idAnnonceProprietaireChien=?;");
            pre.setString(1, description);
            pre.setString(2, datePerte);
            pre.setString(3, localisation);
            pre.setString(4, messageVocal);
            pre.setInt(5, idAnnonceProprietaireChien);
            
            

            if (pre.executeUpdate() != 0) {
                System.out.println(" Annonce Chien Perdu updated");
                 } else {
                System.out.println("Annonce Chien Perdu not updated");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
       public List<AnnonceProprietaireChien> afficherAnnonceAccouplement() throws SQLException {
        List<AnnonceProprietaireChien> annoncesAccouplement = new ArrayList<>();
        String req = "select * from annonce_proprietaire_chien a join chien c ON a.idChien=c.idChien join proprietaire_chien pc on pc.idProprietaireChien=c.idProprietaireChien join individu i on i.idIndividu=pc.idIndividu where type='A';";
        ste = connect.createStatement();
        ResultSet rst = ste.executeQuery(req);

        while (rst.next()) {
            System.out.println(rst.getInt("pc.idProprietaireChien"));
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            ProprietaireChien npc=new ProprietaireChien(rst.getInt("idProprietaireChien"),ni,rst.getString("bio"));
            Chien nc = new Chien(rst.getInt("idChien"),npc,rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"));
            AnnonceProprietaireChien a = new AnnonceProprietaireChien(rst.getInt("idAnnonceProprietaireChien"),
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("type"),
            rst.getString("localisation"));
         
            
            annoncesAccouplement.add(a);
        }
        return annoncesAccouplement;
    } 
public List<AnnonceProprietaireChien> FilterAnnonceChienPerduByLocation(String localisation) throws SQLException {
        List<AnnonceProprietaireChien> annoncesAccouplement = new ArrayList<>();
        PreparedStatement ps = connect.prepareStatement("select * from annonce_proprietaire_chien where type='A' and localisation = ?;");
        ps.setString(1,localisation);
        ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            Chien nc = new Chien(rst.getInt("idChien"));
            AnnonceProprietaireChien a = new AnnonceProprietaireChien(rst.getInt("idAnnonceProprietaireChien"),
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("type"),
            rst.getDate("datePerte"),
            rst.getString("localisation"),
            rst.getString("messageVocal"));
         
            
            annoncesAccouplement.add(a);
        }
        return annoncesAccouplement;
    }
*/
}


