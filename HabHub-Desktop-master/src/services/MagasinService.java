/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Magasin;
import entities.Utilisateur;
import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author asus
 */
public class MagasinService implements IMagasin<Magasin> {
    Connection connexion;
      Statement stm;

    public MagasinService() {
                connexion = MyDB.getInstance().getConnexion();

    }
      

    @Override
    public void ajouterMa(Magasin ma) throws SQLException {
        
        PreparedStatement preUser = connexion.prepareStatement("INSERT INTO utilisateur (email,password,numTel,type)VALUES (?,?,?,?);");
        preUser.setString(1, ma.getUser().getEmail());
        preUser.setString(2, ma.getUser().getPassword());
        preUser.setInt(3, ma.getUser().getNumTel());
        preUser.setString(4, ma.getUser().getType());
        preUser.executeUpdate();


        PreparedStatement stm = connexion.prepareStatement("select idUtilisateur from utilisateur where email =?;");
        stm.setString(1, ma.getUser().getEmail());
        ResultSet rst = stm.executeQuery();

        Utilisateur u = new Utilisateur ();
        while (rst.next()){
        u.setIdUtilisateur(rst.getInt("idUtilisateur"));
        }
String req = "INSERT INTO `magasin` (  idUtilisateur,codePostal,cinRepLegal,RIB,nomMagasin,nomGestionnaireMagasin,adresse,ville,nomRepLegal,matriculeFiscale,identifiantFiscal)  "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
      
        ps.setInt(1, u.getIdUtilisateur());
        ps.setInt(2, ma.getCodePostal());
        ps.setString(3, ma.getCinRL());
        ps.setInt(4, ma.getRib());
        ps.setString(5, ma.getNomMag());
        ps.setString(6, ma.getNomGest());
        ps.setString(7, ma.getAdresse());
        ps.setString(8, ma.getVille());
        ps.setString(9, ma.getNomRL());
        ps.setString(10, ma.getMatricule());
        ps.setString(11, ma.getIdFiscal());
       
       
       
        ps.executeUpdate();    }

    @Override
    public List<Magasin> afficherMagasin() throws SQLException {
        List<Magasin> magasins = new ArrayList<>();
        String req = "select * from magasin";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Utilisateur U=new Utilisateur(rst.getInt("idUtilisateur"));
            Magasin ma = new Magasin(
                    rst.getInt("IdMagasin"),
                    U,
                    rst.getInt("codePostal"),
                    rst.getString("cinRepLegal"),//or rst.getInt(1) ,,,,,,,,
                  rst.getInt("RIB"),
                    rst.getString("nomMagasin"),
                    rst.getString("nomGestionnaireMagasin"),
                    rst.getString("adresse"),
                    rst.getString("ville"),
                 
                    rst.getString("nomRepLegal"),
                    rst.getString("matriculeFiscale"),
                    rst.getString("identifiantFiscal"),
                    rst.getByte("patente")
                   
            )
                    ;
                    
            magasins.add(ma);
        }
           return magasins;    }

    @Override
    public void deleteMagasin(int idMagasin) throws SQLException {
String req = "delete from magasin where idMagasin =?";
        PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, idMagasin);
            ps.executeUpdate();    }

    @Override
    public boolean updateMagasin(int idMagasin, int idUtilisateur, int codePostal, String cinRL, int RIB, String nomMag, String nomGest, String adresse, String ville, String nomRL, String matricule, String idFiscal) {
 try {

            PreparedStatement pre = connexion.prepareStatement("UPDATE magasin SET idMagasin = ? , idUtilisateur= ? ,codePostal = ? , cinRepLegal= ?,RIB = ? , nomMagasin= , nomGestionnaireMagasin= , adresse= , ville= , nomRepLegal= , matriculeFiscale= , identifiantFiscal= , ?  where idMagasin= ? ;");
           pre.setInt(1, idUtilisateur);
            pre.setInt(2, codePostal);  
            pre.setString(3, cinRL);
            pre.setInt(4, RIB);    //idUtilisateur,codePostal,cinRepLegal,RIB,nomMagasin,nomGestionnaireMagasin,adresse,ville,nomRepLegal,matriculeFiscale,identifiantFiscal
            pre.setString(5, nomMag);
            pre.setString(6, nomGest);   
            pre.setString(7, adresse);
            pre.setString(8, ville);  
            pre.setString(9, nomRL);
            pre.setString(10, matricule);   
            pre.setString(11, idFiscal);
            pre.setInt(12, idMagasin);
            if (pre.executeUpdate() != 0) {
                System.out.println(" magasin Updated");
                 } else {
                System.out.println("not Updated");
            }
                return true;
            
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
                                        }
        return false;
    }    }
    

