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

public class LikesService {

    private Connection connect;
    private Statement ste;

    public LikesService() {
        connect = MyDB.getInstance().getConnexion();

    }

    public int getLikeByDogId(int id) throws SQLException {
        List<Chien> chiens = new ArrayList();
        int i = 0;
        String req = "select count(*) from likes where idChien=?";
        PreparedStatement ps = connect.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rst = ps.executeQuery();

        while (rst.next()) {

            i = rst.getInt(1);

        }
        return i;

    }
    
    public void ajouterLike(int idIndividu,int idChien) {
        try {
            PreparedStatement pre = connect.prepareStatement("INSERT INTO likes (idIndividu, idChien)VALUES (?,?);");
            pre.setInt(1, idIndividu);
            pre.setInt(2, idChien);
            pre.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public void supprimerLike(int idIndividu,int idChien) {
        try {
            PreparedStatement pre = connect.prepareStatement("delete from likes where idIndividu=? and idChien=?;");
            pre.setInt(1, idIndividu);
            pre.setInt(2, idChien);
            pre.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean checkLike(int idIndividu,int idChien) throws SQLException {
        List<Chien> chiens = new ArrayList();
        int i = 0;
        boolean res=false;
        String req = "select * from likes where idIndividu=? and idChien=?";
        PreparedStatement ps = connect.prepareStatement(req);
        ps.setInt(1, idIndividu);
         ps.setInt(2, idChien);
        ResultSet rst = ps.executeQuery();

        if (rst.next()) {

            res=true;

        }
        return res;

    }
    
}
