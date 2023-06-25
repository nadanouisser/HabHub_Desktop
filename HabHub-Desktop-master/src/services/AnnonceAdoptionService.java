/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.AnnonceAdoption;
import entities.Chien;
import entities.Individu;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Tun21
 */
public class AnnonceAdoptionService implements IAnnonceAdoption<AnnonceAdoption> {
    
    Connection connexion;
    Statement stm;

    public AnnonceAdoptionService() {
        connexion = MyDB.getInstance().getConnexion();
    }
    
    

    @Override
    public void addAnnonceAdoption(AnnonceAdoption a) throws SQLException {
        String req = "insert into annonce_adoption (idIndividu,idChien,datePublication,description,localisation) values (?,?,SYSDATE(),?,?)";
        PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, a.getIdIndividu().getIdIndividu());
            ps.setInt(2, a.getIdChien().getIdChien());
            ps.setString(3, a.getDescription());
            ps.setString(4, a.getLocalisation());
            ps.executeUpdate();
            
    }


    public void updateAnnonceAdoption(Chien c, AnnonceAdoption a) throws SQLException {
        PreparedStatement pre = connexion.prepareStatement("UPDATE chien SET nom= ? , sexe= ?, age= ?, vaccination= ?, description= ?,color=?, race=?, groupe=? where idChien=?;");
         pre.setString(1, c.getNom());
            pre.setString(2, c.getSexe());
            pre.setString(3, c.getAge());
            pre.setBoolean(4, c.getVaccination());
            pre.setString(5, c.getDescription());
           
            pre.setString(6, c.getColor());
            pre.setString(7, c.getRace());
            pre.setString(8, c.getGroupe());
            pre.setInt(9, c.getIdChien());
            
        String req = "update annonce_adoption set idChien=?,  description=? where idAnnonceAdoption =?";
        PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, c.getIdChien());
           
            ps.setString(2, c.getDescription());
            ps.setInt(3, a.getIdAnnonceAdoption());
            ps.executeUpdate();
            
        
    }
     public boolean updateChien(int idChien,String nom,String sexe,String age,boolean vaccination,String description,String image,String color,String race,String groupe) {
            try {
            PreparedStatement pre = connexion.prepareStatement("UPDATE chien SET nom= ? , sexe= ?, age= ?, vaccination= ?, description= ?, image=?, color=?, race=?, groupe=? where idChien=?;");
            pre.setString(1, nom);
            pre.setString(2, sexe);
            pre.setString(3, age);
            pre.setBoolean(4, vaccination);
            pre.setString(5, description);
            pre.setString(6, image);
            pre.setString(7, color);
            pre.setString(8, race);
            pre.setString(9, groupe);
            pre.setInt(10, idChien);
            

            if (pre.executeUpdate() != 0) {
                System.out.println(" Chien updated");
                 } else {
                System.out.println("Chien not updated");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public void deleteAnnonceAdoption(int id) throws SQLException {
        String req = "delete from annonce_adoption where idAnnonceAdoption =?";
        PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            
    }

    @Override
    public List displayAnnonceAdoption() throws SQLException {
        List<AnnonceAdoption> listeannonces = new ArrayList<AnnonceAdoption>();

        String req = "select * from annonce_adoption a join chien c on a.idChien=c.idChien join individu i on a.idIndividu=i.idIndividu where status='P' order by datePublication desc";
        stm = connexion.createStatement();
            //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        //IChien cs = ChienService.getInstance();
        //ChienService cs = new ChienService();
        while (rst.next()) {
            /*AnnonceAdoption a = new AnnonceAdoption(rst.getInt("idAnnonceAdoption"),rst.getInt("idIndividu"),rst.getInt("idChien"),rst.getDate("datePublication"),
            rst.getString("description"),rst.getString("localisation"));*/
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getInt("idChien"),rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("image")
            ,rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceAdoption a = new AnnonceAdoption(
            rst.getInt("idAnnonceAdoption"),
            ni,
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("localisation"));
            
            listeannonces.add(a);
        }
        return listeannonces;
    }
       //select * from chien join annonce_adoption from a annonce_adoption, c chien on c.idChien=a.idChien where idChien=?

    @Override
    public List<AnnonceAdoption> rechercheAnnonceAdoption(String nom) throws SQLException {
        List<AnnonceAdoption> annonce = new ArrayList<>();
        //List<Chien> chiens = new ArrayList<>();
        String req = "select * from chien c join annonce_adoption a on c.idChien=a.idChien join individu i on a.idIndividu=i.idIndividu where c.nom like ? or i.nom like ? or a.localisation like ?";
        PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, "%" + nom+ "%");
            ps.setString(2, "%" + nom+ "%");
            ps.setString(3, "%" + nom+ "%");
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
                                       rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("image"),
                                 rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceAdoption a = new AnnonceAdoption(
            rst.getInt("idAnnonceAdoption"),
            ni,
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("localisation"));
                
                annonce.add(a);
            }
            return annonce;
    }
    public List<AnnonceAdoption> FilterAnnonceAdoption(String filtre,String valeur) throws SQLException {
        List<AnnonceAdoption> listeannonces= new ArrayList<>();
        PreparedStatement ps = connexion.prepareStatement("select * from annonce_adoption a join chien c ON a.idChien=c.idChien join individu i on a.idIndividu=i.idIndividu where  "+filtre+" = ?;");
        ps.setString(1,valeur);
        ResultSet rst = ps.executeQuery();
        while (rst.next()) {
            /*AnnonceAdoption a = new AnnonceAdoption(rst.getInt("idAnnonceAdoption"),rst.getInt("idIndividu"),rst.getInt("idChien"),rst.getDate("datePublication"),
            rst.getString("description"),rst.getString("localisation"));*/
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("image"),
                                 rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceAdoption a = new AnnonceAdoption(
            rst.getInt("idAnnonceAdoption"),
            ni,
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("localisation"));
            
            listeannonces.add(a);
        }
        return listeannonces;
    }
     public int getAnnonceAdoptionNumberByIdIndividu(int id) throws SQLException {
         int i=0;
       PreparedStatement req = connexion.prepareStatement("select count(*) from annonce_adoption where idIndividu=?");
            req.setInt(1,id);
   
             ResultSet rst= req.executeQuery();
            while(rst.next())
             {
              i= rst.getInt(1);
             }
            return i;
 
    }
    
     
     
     
     
     
     
     
    public HashSet<String> afficherLocation() throws SQLException {
  
        HashSet<String> annonce = new HashSet<>();
        String req = "select localisation from annonce_adoption";
        PreparedStatement ps = connexion.prepareStatement(req);
            ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            
            annonce.add(rst.getString(1));
        }
        return annonce;
    }
    
    
    
    
    
    
    
    
    
    public List<AnnonceAdoption> afficherAnnonceLocation(String Filtre) throws SQLException {
  
        List<AnnonceAdoption> annonce = new ArrayList<>();
        String req = "select * from annonce_adoption a join chien c on a.idChien=c.idChien join individu i on a.idIndividu=i.idIndividu where a.localisation like ? ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, "%" + Filtre+ "%");
            ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getInt("idChien"),rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("image")
            ,rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceAdoption a = new AnnonceAdoption(
            ni,
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("localisation"));
            annonce.add(a);
        }
        return annonce;
    }
    
    
    
    public HashSet<String> afficherDogName() throws SQLException {
  
        HashSet<String> annonce = new HashSet<>();
        String req = "select nom from annonce_adoption a join chien c where c.idChien=a.idChien";
        PreparedStatement ps = connexion.prepareStatement(req);
            ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            
            annonce.add(rst.getString(1));
        }
        return annonce;
    }
    
    
    public List<AnnonceAdoption> afficherAnnonceDogName(String Filtre) throws SQLException {
  
        List<AnnonceAdoption> annonce = new ArrayList<>();
        String req = "select * from annonce_adoption a join chien c on a.idChien=c.idChien join individu i on a.idIndividu=i.idIndividu where c.nom like ? ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, "%" + Filtre+ "%");
            ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getInt("idChien"),rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("image")
            ,rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceAdoption a = new AnnonceAdoption(
            ni,
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("localisation"));
            annonce.add(a);
        }
        return annonce;
    }
    
    
    
    
    
    
    public HashSet<String> afficherUserName() throws SQLException {
  
        HashSet<String> annonce = new HashSet<>();
        String req = "select nom from annonce_adoption a join individu i where i.idIndividu=a.idIndividu";
        PreparedStatement ps = connexion.prepareStatement(req);
            ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            
            annonce.add(rst.getString(1));
        }
        return annonce;
    }
    
    
    
    
    
    
    
    public List<AnnonceAdoption> afficherAnnonceUserName(String Filtre) throws SQLException {
  
        List<AnnonceAdoption> annonce = new ArrayList<>();
        String req = "select * from annonce_adoption a join chien c on a.idChien=c.idChien join individu i on a.idIndividu=i.idIndividu where i.nom like ? ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, "%" + Filtre+ "%");
            ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getInt("idChien"),rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("image")
            ,rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceAdoption a = new AnnonceAdoption(
            ni,
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("localisation"));
            annonce.add(a);
        }
        return annonce;
    }
    
    
    
    
    
    
    public List<AnnonceAdoption> afficherAnnonceVaccinated(int i) throws SQLException {
  
        List<AnnonceAdoption> annonce = new ArrayList<>();
        String req = "select * from annonce_adoption a join chien c on a.idChien=c.idChien join individu i on a.idIndividu=i.idIndividu where c.vaccination =? ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, i);
            ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getInt("idChien"),rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("image")
            ,rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceAdoption a = new AnnonceAdoption(
            ni,
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("localisation"));
            annonce.add(a);
        }
        return annonce;
    }
    
    
    
    
    
    
    
    public List<AnnonceAdoption> afficherAnnonceNoneVaccinated(int o) throws SQLException {
  
       
        List<AnnonceAdoption> annonce = new ArrayList<>();
        String req = "select * from annonce_adoption a join chien c on a.idChien=c.idChien join individu i on a.idIndividu=i.idIndividu where c.vaccination =? ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, o);
        ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getInt("idChien"),rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("image")
            ,rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceAdoption a = new AnnonceAdoption(
            ni,
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("localisation"));
            annonce.add(a);
        }
        return annonce;
    }
    
    
    
    
    
    
    
    
    public List<AnnonceAdoption> afficherAnnonceMale() throws SQLException {
  
        
        List<AnnonceAdoption> annonce = new ArrayList<>();
        String req = "select * from annonce_adoption a join chien c on a.idChien=c.idChien join individu i on a.idIndividu=i.idIndividu where c.sexe like 'm' ";
        PreparedStatement ps = connexion.prepareStatement(req);
            ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getInt("idChien"),rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("image")
            ,rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceAdoption a = new AnnonceAdoption(
            ni,
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("localisation"));
            annonce.add(a);
        }
        return annonce;
    }
    public List<AnnonceAdoption> afficherAnnoncefemale() throws SQLException {
  
        
        List<AnnonceAdoption> annonce = new ArrayList<>();
        String req = "select * from annonce_adoption a join chien c on a.idChien=c.idChien join individu i on a.idIndividu=i.idIndividu where c.sexe like 'f' ";
        PreparedStatement ps = connexion.prepareStatement(req);
            ResultSet rst = ps.executeQuery();

        while (rst.next()) {
            
            Utilisateur nu= new Utilisateur(rst.getInt("idUtilisateur"));
            Individu  ni= new Individu(rst.getInt("idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getDate("dateNaissance"),rst.getString("adresse"),
            rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"));
            Chien nc = new Chien(rst.getInt("idChien"),rst.getString("c.nom"),rst.getString("c.sexe"),rst.getString("age"),rst.getBoolean("vaccination"),rst.getString("description"),rst.getString("image")
            ,rst.getString("color"),rst.getString("race"),rst.getString("groupe"));
            AnnonceAdoption a = new AnnonceAdoption(
            ni,
            nc,
            rst.getDate("datePublication"),
            rst.getString("description"),
            rst.getString("localisation"));
            annonce.add(a);
        }
        return annonce;
    }
    

    /*@Override
    public List<AnnonceAdoption> afficherAnnonceParIndividu(String nom) throws SQLException {
        List<AnnonceAdoption> annonce = new ArrayList<>();
       AnnonceAdoption a = new AnnonceAdoption();
        String req = "select * from chien where idIndividu=? ";
        PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, nom);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                a.setIdAnnonceAdoption(rst.getInt(1));
                a.setIdIndividu(rst.getInt(2));
                a.setIdChien(rst.getInt(3));
                a.setDatePublication(rst.getDate(4));
                a.setDescription(rst.getString(5));
                a.setLocalisation(rst.getString(6));
                
                annonce.add(a);
            }
            return annonce;
    }*/
}
