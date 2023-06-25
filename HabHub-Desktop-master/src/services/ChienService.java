package services;

import entities.Chien;
import entities.Individu;
import entities.Utilisateur;
import utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Statics;

public class ChienService {

    private Connection connect;
    private Statement ste;

    public ChienService() {
        connect = MyDB.getInstance().getConnexion();

    }

    public void ajouterChienProprietaire(Chien c) {
        try {
            PreparedStatement pre = connect.prepareStatement("INSERT INTO chien (idIndividu, nom,sexe,age,vaccination,description,image,color,race,groupe)VALUES (?,?,?,?,?,?,?,?,?,?);");
            pre.setInt(1, c.getIndividu().getIdIndividu());
            pre.setString(2, c.getNom());
            pre.setString(3, c.getSexe());
            pre.setString(4, c.getAge());
            pre.setBoolean(5, c.getVaccination());
            pre.setString(6, c.getDescription());
            pre.setString(7, c.getImage());
            pre.setString(8, c.getColor());
            pre.setString(9, c.getRace());
            pre.setString(10, c.getGroupe());
            pre.executeUpdate();
            PreparedStatement updatepre = connect.prepareStatement("update individu set proprietaireChien=1 where idIndividu=?;");
            updatepre.setInt(1, Statics.currentIndividu.getIdIndividu());
            updatepre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

        public void ajouterChienSansProprietaire(Chien c) throws SQLException {
        PreparedStatement pre = connect.prepareStatement("INSERT INTO chien (nom,sexe,age,vaccination,description,image,color,race,groupe)VALUES (?,?,?,?,?,?,?,?,?);");
        pre.setString(1,c.getNom());
        pre.setString(2,c.getSexe());
        pre.setString(3,c.getAge());
        pre.setBoolean(4,c.getVaccination());
        pre.setString(5,c.getDescription());
        pre.setString(6,c.getImage());
        pre.setString(7,c.getColor());
        pre.setString(8, c.getRace());
        pre.setString(9, c.getGroupe());
        pre.executeUpdate();
    }
    
     public void ajouterChienSansProprietaireb(Chien c) throws SQLException {
        PreparedStatement pre = connect.prepareStatement("INSERT INTO chien (nom,age,description,color,race,groupe)VALUES (?,?,?,?,?,?);");
        pre.setString(1, c.getNom());
        pre.setString(2, c.getAge());
        pre.setString(3, c.getDescription());
        pre.setString(4, c.getColor());
        pre.setString(5, c.getRace());
        pre.setString(6, c.getGroupe());
        pre.executeUpdate();
    }

    public boolean updateChien(int idChien, Chien c) {
        try {
            PreparedStatement pre = connect.prepareStatement("UPDATE chien SET nom= ? , age= ?, description= ? , color=? , race = ? , groupe = ? where idChien=?;");
             pre.setString(1, c.getNom());
        pre.setString(2, c.getAge());
        pre.setString(3, c.getDescription());
        pre.setString(4, c.getColor());
        pre.setString(5, c.getRace());
        pre.setString(6, c.getGroupe());
            pre.setInt(7, idChien);

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

    public boolean delete(int idChien) throws SQLException {

        PreparedStatement pre = connect.prepareStatement("Delete from chien where IdChien=? ;");
        pre.setInt(1, idChien);
        if (pre.executeUpdate() != 0) {
            System.out.println("Chien Deleted");
            return true;
        }
        System.out.println("Chien not found");
        return false;

    }

    public List<Chien> afficherChiens() throws SQLException {
        List<Chien> chiens = new ArrayList<>();
        String req = "select * from chien c LEFT JOIN individu i on i.idIndividu=c.idIndividu left join utilisateur u on u.idUtilisateur=i.idUtilisateur;";
        ste = connect.createStatement();
        ResultSet rst = ste.executeQuery(req);

        while (rst.next()) {
             Utilisateur nu= new Utilisateur(rst.getInt("u.idUtilisateur"));
            Individu  i= new Individu(rst.getInt("i.idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getString("sexe"),rst.getDate("dateNaissance"),rst.getString("adresse"),
                    rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"),rst.getBoolean("proprietaireChien"));
            Chien a = new Chien(rst.getInt("idChien"),
                    i,
                    rst.getString("nom"),
                    rst.getString("sexe"),
                    rst.getString("age"),
                    rst.getBoolean("vaccination"),
                    rst.getString("description"),
                    rst.getString("image"),
                    rst.getString("color"),
                    rst.getString("race"),
                    rst.getString("groupe"));

            chiens.add(a);
        }
        return chiens;
    }
    
     public List<Chien> rechercherChiens(String input) throws SQLException {
        List<Chien> chiens = new ArrayList<>();
        String req = "select * from chien c LEFT JOIN individu i on i.idIndividu=c.idIndividu left join utilisateur u on u.idUtilisateur=i.idUtilisateur where c.nom like ? or i.prenom like ? or age like ? or description like ? or race like ? or groupe like ? or color like ?;";
        PreparedStatement ps = connect.prepareStatement(req);
        ps.setString(1, "%" + input+ "%");
         ps.setString(2, "%" + input+ "%");
          ps.setString(3, "%" + input+ "%");
           ps.setString(4, "%" + input+ "%");
            ps.setString(5, "%" + input+ "%");
             ps.setString(6, "%" + input+ "%");
             ps.setString(7, "%" + input+ "%");
        ResultSet rst = ps.executeQuery();

        while (rst.next()) {
             Utilisateur nu= new Utilisateur(rst.getInt("u.idUtilisateur"));
            Individu  i= new Individu(rst.getInt("i.idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getString("sexe"),rst.getDate("dateNaissance"),rst.getString("adresse"),
                    rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"),rst.getBoolean("proprietaireChien"));
            Chien a = new Chien(rst.getInt("idChien"),
                    i,
                    rst.getString("nom"),
                    rst.getString("sexe"),
                    rst.getString("age"),
                    rst.getBoolean("vaccination"),
                    rst.getString("description"),
                    rst.getString("image"),
                    rst.getString("color"),
                    rst.getString("race"),
                    rst.getString("groupe"));

            chiens.add(a);
        }
        return chiens;
    }

    public Chien findChienById(int id) throws SQLException {
        Chien c = new Chien();
        String req = "select * from chien where idChien=? ";
        PreparedStatement ps = connect.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rst = ps.executeQuery();
        while (rst.next()) {
            c.setIdChien(rst.getInt(1));
            c.getIndividu().setIdIndividu(rst.getInt(2));
            c.setNom(rst.getString(3));
            c.setSexe(rst.getString(4));
            c.setAge(rst.getString(5));
            c.setVaccination(rst.getBoolean(6));
            c.setDescription(rst.getString(7));
        }
        return c;
    }

    public List<Chien> findChienByIndividu(int id) throws SQLException {
        List<Chien> chiens = new ArrayList();
        String req = "select * from chien where idIndividu=? ";
        PreparedStatement ps = connect.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rst = ps.executeQuery();
        while (rst.next()) {
            Individu i = new Individu(rst.getInt(2));
            Chien c = new Chien(rst.getInt("idChien"), i, rst.getString("nom"), rst.getString("sexe"), rst.getString("age"), rst.getBoolean("vaccination"),
                     rst.getString("description"), rst.getString("image"), rst.getString("color"), rst.getString("race"), rst.getString("groupe"),rst.getBoolean("playWithMe"));
            chiens.add(c);
        }
        return chiens;
    }
    
   
    
        public List<Chien> findChienByLocation(String location) throws SQLException {
        List<Chien> chiens = new ArrayList();
        String req = "select * from chien c join individu i on i.idIndividu=c.idIndividu join utilisateur u on u.idUtilisateur=i.idUtilisateur  where i.adresse=? and c.idIndividu!=?";
        PreparedStatement ps = connect.prepareStatement(req);
        ps.setString(1, location);
        ps.setInt(2, Statics.currentIndividu.getIdIndividu());
        ResultSet rst = ps.executeQuery();
        while (rst.next()) {
            Utilisateur nu= new Utilisateur(rst.getInt("u.idUtilisateur"));
            
            Individu  i= new Individu(rst.getInt("i.idIndividu"),nu,rst.getString("i.nom"),rst.getString("prenom"),rst.getString("sexe"),rst.getDate("dateNaissance"),rst.getString("adresse"),
                    rst.getString("facebook"),rst.getString("instagram"),rst.getString("whatsapp"),rst.getBoolean("proprietaireChien"));
                    
            Chien c = new Chien(rst.getInt("idChien"), i, rst.getString("nom"), rst.getString("sexe"), rst.getString("age"), rst.getBoolean("vaccination"),
                     rst.getString("description"), rst.getString("image"), rst.getString("color"), rst.getString("race"), rst.getString("groupe"),rst.getBoolean("playWithMe"));
            chiens.add(c);
        }
        return chiens;
    }
      
       public void playWithMeHandler(int idChien , int valeur) throws SQLException {

        PreparedStatement pre = connect.prepareStatement("update chien set playWithMe=? where idChien=? ;");
        pre.setInt(1, valeur);
        pre.setInt(2, idChien);
        pre.executeUpdate();
            
        
    
    }  
         public Chien fetchAddedChien() throws SQLException {
       Chien c = new Chien();
        String req = "SELECT * FROM chien WHERE idChien = (SELECT MAX(idChien) FROM chien)";
        ste = connect.createStatement();
            ResultSet rst = ste.executeQuery(req);
            while (rst.next()) {
                Individu ni = new Individu(rst.getInt("idIndividu"));
                Chien a = new Chien(rst.getInt("idChien"),
                ni,
                rst.getString("nom"),
                rst.getString("sexe"),
                rst.getString("age"),
                rst.getBoolean("vaccination"),
                rst.getString("description"),
                rst.getString("image"),
                rst.getString("color"),
                rst.getString("race"),
                rst.getString("groupe"));
                c=a;
                }
                return c;
    }
        

}
