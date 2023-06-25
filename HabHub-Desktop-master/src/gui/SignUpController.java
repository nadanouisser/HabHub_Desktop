/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Individu;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.UserIndividuServices;
import services.UtilisateurService;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author NADA_USER
 */
public class SignUpController implements Initializable {

   
     @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private TextField numtel;
    
    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    void SwitchSceneLogIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/log_in.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

       @FXML
    void SwitchSceneLogeedIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/log_in.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
   @FXML
     void signup (ActionEvent event )throws IOException, SQLException
     {

          UserIndividuServices i = new UserIndividuServices();
            UtilisateurService us = new UtilisateurService();

            Utilisateur U = new Utilisateur();
             Individu p = new Individu();


            U.setEmail(email.getText());
                 U.setPassword(password.getText());
                 U.setNumTel(  Integer.parseInt(numtel.getText()));
                 U.setType("I");
                 p.setPrenom(name.getText());
                 p.setUtilisateur(U);

                i.ajouter(p, U);

              Statics.currentIndividu= i.findIndividuByIdUtilisateur(us.chercherUtilisateur(U.getEmail()));

                SwitchSceneLogeedIn(event);



           }
     
   

  




     
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    

}
