/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import entities.Utilisateur;
import entities.Individu;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.UserIndividuServices;
import services.UtilisateurService;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author Ed
 */
public class UsersPopUpController implements Initializable {

        @FXML
    private TextField name;

    @FXML
    private TextField prenom;

    @FXML
    private TextField datenaiss;

    @FXML
    private TextField sexe;

    @FXML
    private TextField adresse;

    @FXML
    private TextField facebook;

    @FXML
    private TextField instagram;

    @FXML
    private TextField whatsapp;

    

 

   
    private int idUpdate;
       UserIndividuServices us = new UserIndividuServices();
         
         
         
   private boolean update;

    @FXML
    void submit(MouseEvent event) throws SQLException  {
 
        Individu i = new Individu(name.getText(),
        prenom.getText(),
        adresse.getText(),
        sexe.getText(),
        facebook.getText(),
        instagram.getText(),
        whatsapp.getText());
       
        if (update) {
         
          us.Update(i); 
        }
        else{
        Utilisateur u = new Utilisateur();
        us.ajouter(i , u);
        
        }
        clean();
       
                 

    }

    @FXML
    private void clean() {
        name.setText(null);
        prenom.setText(null);
        datenaiss.setText(null);
        adresse.setText(null);
       facebook.setText(null);
        instagram.setText(null);
        whatsapp.setText(null);
        sexe.setText(null);
        
        

    }
    void setTextField(String nom, String prenom, String dateNaissance, String sexe ,String adresse,String facebook  , String instagram,String whatsapp) {

        this.name.setText(nom);
        this.prenom.setText(prenom);
        this.adresse.setText(adresse);this.datenaiss.setText(dateNaissance);
        this.sexe.setText(sexe);this.adresse.setText(adresse);
        this.facebook.setText(facebook);this.instagram.setText(whatsapp);this.sexe.setText(whatsapp);
    }
    
     void setIdUpdate(int i) {
        this.idUpdate = i;

    }
    
void setUpdate(boolean b) {
        this.update = b;

    }



    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
