/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import HabHub.Main;
import entities.Utilisateur;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.UtilisateurService;
import services.UserIndividuServices;
import utils.Statics;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author NADA_USER
 */
public class Log_inController implements Initializable {

    /* @FXML
         private TextField email;
        @FXML
         private TextField pswd;*/
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Button login;

    @FXML
    private Text wrongaccess;
    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    void switchSceneSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/SignUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    void switchSceneProfile(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("../gui/BusinessFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    void switchSceneBackOffice(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("../gui/backOffice/HomeBackOffice.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void Forgotpassword(ActionEvent event)throws IOException, SQLException {
          Parent root = FXMLLoader.load(getClass().getResource("../gui/EnterMail.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void seConnecter(ActionEvent event) throws IOException, SQLException {
     
        Utilisateur u = new Utilisateur();
        UtilisateurService us = new UtilisateurService();
        UserIndividuServices uis = new UserIndividuServices();
        if (us.verifLogin(email.getText(), password.getText())) {
           
            u=us.chercherUtilisateur(email.getText());
            Statics.currentIndividu = uis.findIndividuByIdUtilisateur(us.chercherUtilisateur(email.getText()));
            if (u.getType().equals("A")){
                switchSceneBackOffice(event);
            }
            else{
            
            switchSceneProfile(event);
            }
        } else {
            wrongaccess.setText("Incorrect email or password");
        }
        

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}


